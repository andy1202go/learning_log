/******************************************************************************
 * Copyright (C) 2013 - 2022 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package org.shithappens.libs.network;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Hashtable;

/**
 * @author liangbo
 * @version V1.0
 * @Title: DNS_Server.java
 * @Package org.shithappens.libs.network
 * @Description
 * @date 2022 08-04 11:18.
 */
public class DNS_Server {

    public static void main(String args[]) throws Exception {
        int port_number;

        //checking if the user chose a port number instead of the default (8888)
        if (args.length == 0) {
            System.out.println("-----------------------------");
            System.out.println("Usage: java DNS_Server <port>");
            System.out.println("Default port: 8888");
            System.out.println("-----------------------------\n");
            port_number = 8888;
        } else {
            port_number = Integer.parseInt(args[0]);
        }

        Hashtable<String, String> addresses = new Hashtable<String, String>();  //the hashtable where all the hostnames and their IP addresses will be stored

        File file = new File("hosts.txt");
        FileReader file_reader = new FileReader(file);
        BufferedReader reader = new BufferedReader(file_reader);

        String line_of_file;

        //reading the hostnames and their addresses from hosts.txt
        while ((line_of_file = reader.readLine()) != null) {
            String[] columns = line_of_file.split(" ");
            addresses.put(columns[0], columns[1]);
        }

        reader.close();

//        addresses.put("localhost", "127.0.0.1");

        System.out.println("DNS Server is running at port " + port_number + "...");

        //setting up the socket to communicate back and forth to the client
        DatagramSocket server_socket = new DatagramSocket(port_number);
        byte[] receive_data = new byte[1024];
        byte[] send_data = new byte[1024];
        //waiting for a client request
        while (true) {
            //receiving the udp packet of data from client and turning them to string to print them
            DatagramPacket receive_packet = new DatagramPacket(receive_data, receive_data.length);
            server_socket.receive(receive_packet);
            String given_hostname = new String(receive_packet.getData(), 0, receive_packet.getLength());

            String found_address = "-1";

            //searching the IP address of the given hostname on the hashtable
            if (addresses.get(given_hostname) != null) {
                System.out.println("Client's request: " + given_hostname + " | IP address: " + addresses.get(given_hostname));
                found_address = addresses.get(given_hostname);
            } else {
                try {
                    //searching the IP address of the given hostname on the internet
                    InetAddress address_search = java.net.InetAddress.getByName(given_hostname);
                    found_address = address_search.getHostAddress();

                    System.out.println("Client's request: " + given_hostname + " | IP address: " + found_address);

                    addresses.put(given_hostname, found_address);              //adding the new hostname and its address to the address hashtable

                    //appending the new hostname with its address to hosts.txt
                    PrintWriter writer = new PrintWriter(new FileOutputStream(new File("hosts.txt"), true), true);
                    writer.append("\n" + given_hostname + " " + found_address);
                    writer.close();
                } catch (UnknownHostException ex) {
                    System.out.println("Client's domain request: " + given_hostname + " | IP address: Not found");
                }
            }

            //getting the address and port of client
            InetAddress client_address = receive_packet.getAddress();
            int client_port = receive_packet.getPort();

            //sending the found address back to client
            send_data = found_address.getBytes();
            DatagramPacket send_packet = new DatagramPacket(send_data, send_data.length, client_address, client_port);
            server_socket.send(send_packet);
        }
    }
}
