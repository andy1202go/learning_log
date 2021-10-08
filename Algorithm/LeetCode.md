# LeetCode

[toc]

## 逐题解决

题目：

> d

思路：

- 

代码：

```java

```

结果和分析：

- 结果：性能等
- 分析：

进阶：



```java

```

标签：

### 1 两数之和

题目：

> 给定一个整数数组 `nums` 和一个整数目标值 `target`，请你在该数组中找出 **和为目标值** *`target`* 的那 **两个** 整数，并返回它们的数组下标。
>
> 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
>
> 你可以按任意顺序返回答案。
>
>  
>
> **示例 1：**
>
> ```
> 输入：nums = [2,7,11,15], target = 9
> 输出：[0,1]
> 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
> ```
>
> **示例 2：**
>
> ```
> 输入：nums = [3,2,4], target = 6
> 输出：[1,2]
> ```
>
> **示例 3：**
>
> ```
> 输入：nums = [3,3], target = 6
> 输出：[0,1]
> ```
>
>  
>
> **提示：**
>
> - `2 <= nums.length <= 104`
> - `-109 <= nums[i] <= 109`
> - `-109 <= target <= 109`
> - **只会存在一个有效答案**
>
> **进阶：**你可以想出一个时间复杂度小于 `O(n2)` 的算法吗？
>
> Related Topics
>
> 数组
>
> 哈希表

思路：

- 两次遍历看下就行
- 但是性能比较差

代码：

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int result[] = new int[2];
        for (int i = 0; i < nums.length - 1; i++) {
            int left = target - nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == left) {
                    result[0] = i;
                    result[1] = j;
                }
            }
        }
        return result;
    }
}
```

结果和分析：

- 结果：执行耗时:34 ms,击败了43.76% 的Java用户
  			内存消耗:38.5 MB,击败了64.39% 的Java用户
- 分析：O(n<sup>2</sup>)导致

进阶：

- 时间复杂度目标是O(n)
- 增加空间消耗来实现遍历一遍，找到结果
- 存储位置信息即可
- 执行耗时:2 ms,击败了86.22% 的Java用户
  内存消耗:38.3 MB,击败了90.90% 的Java用户

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int result[] = new int[2];
        for (int i = 0; i < nums.length; i++){
            int num = target - nums[i];
            if(map.containsKey(num)){
                result[0] = map.get(num);
                result[1] = i;
                return result;
            }
            map.put(nums[i], i);
        }
        return result;
    }
}
```

总结：

- 利用hash的key-value对的存储信息的能力
- 空间换时间

标签：求和，哈希

## 思路总结

### 1 hash的key-value对的存储信息的能力

### 2 空间换时间

## 参考文献

1 [理清，五花八门的Java日志](https://mp.weixin.qq.com/s/5IV_i_NHGatdhV_XEfCeAQ)



## TODO List

| 时间 | 内容 |      |
| ---- | ---- | ---- |
|      |      |      |
|      |      |      |
|      |      |      |



## 总结

| 时间 | 内容 |      |
| ---- | ---- | ---- |
|      |      |      |
|      |      |      |
|      |      |      |



