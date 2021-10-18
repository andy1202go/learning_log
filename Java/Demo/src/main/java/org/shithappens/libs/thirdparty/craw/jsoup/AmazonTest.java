package org.shithappens.libs.thirdparty.craw.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * 亚马逊的测试
 *
 * @author 80338398
 * @date 2021/10/15
 */
public class AmazonTest {

    private static String URL = "https://www.amazon.in/OPPO-Fantastic-Purple-128GB-Storage/product-reviews/B08VB34KJ1/ref=cm_cr_dp_d_show_all_btm?ie=UTF8&reviewerType=all_reviews";
    private static String dd = "data-hook=\"review\"";

    public static void main(String[] args) {
        //https://www.cnblogs.com/youyoui/p/11065923.html
        Document doc = null;
        try {
            doc = Jsoup.connect(URL).get();
        } catch (IOException e) {
            e.printStackTrace();
        }


        /**
         * <div id="RYWF464PEZY3J" data-hook="review" class="a-section review aok-relative">
         * 							<div id="RYWF464PEZY3J-review-card" class="a-row a-spacing-none">
         * 								<div id="customer_review-RYWF464PEZY3J" class="a-section celwidget">
         * 									<div data-hook="genome-widget" class="a-row a-spacing-mini"><a
         * 											href="/gp/profile/amzn1.account.AFRPJOYYEKSO7VVBWKTWVCRGXV6A/ref=cm_cr_arp_d_gw_btm?ie=UTF8"
         * 											class="a-profile" data-a-size="small">
         * 											<div aria-hidden="true" class="a-profile-avatar-wrapper">
         * 												<div class="a-profile-avatar">
         * 													<img src="https://images-na.ssl-images-amazon.com/images/G/01/x-locale/common/grey-pixel.gif" class="a-lazy-loaded" data-src="https://images-eu.ssl-images-amazon.com/images/S/amazon-avatars-global/default._CR0,0,1024,1024_SX48_.png"/><noscript><img src="https://images-eu.ssl-images-amazon.com/images/S/amazon-avatars-global/default._CR0,0,1024,1024_SX48_.png"/></noscript>
         * 												</div>
         * 											</div>
         * 											<div class="a-profile-content">
         * 												<span class="a-profile-name">Uma Dasari</span></div>
         * 										</a></div>
         * 									<div class="a-row"><a class="a-link-normal" title="5.0 out of 5 stars"
         * 											href="/gp/customer-reviews/RYWF464PEZY3J/ref=cm_cr_arp_d_rvw_ttl?ie=UTF8&amp;ASIN=B08VB34KJ1"><i data-hook="review-star-rating" class="a-icon a-icon-star a-star-5 review-rating"><span class="a-icon-alt">5.0 out of 5 stars</span></i></a><span class="a-letter-space"></span><a
         * 											data-hook="review-title"
         * 											class="a-size-base a-link-normal review-title a-color-base review-title-content a-text-bold"
         * 											href="/gp/customer-reviews/RYWF464PEZY3J/ref=cm_cr_arp_d_rvw_ttl?ie=UTF8&amp;ASIN=B08VB34KJ1">
         *
         *
         *
         *
         *
         *
         *
         *
         *
         * 											<span>Design and display 😍</span>
         *
         * 										</a></div>
         * 									<span data-hook="review-date" class="a-size-base a-color-secondary review-date">Reviewed in India on 3 May 2021</span>
         * 									<div class="a-row a-spacing-mini review-data review-format-strip"><a
         * 											data-hook="format-strip" class="a-size-mini a-link-normal a-color-secondary"
         * 											href="/OPPO-Fantastic-Purple-128GB-Storage/product-reviews/B08VB34KJ1/ref=cm_cr_arp_d_rvw_fmt?ie=UTF8&amp;formatType=current_format">Colour:
         * 											Fantastic
         * 											Purple<i class="a-icon a-icon-text-separator" role="img" aria-label="|"></i>Size
         * 											name:
         * 											Large<i class="a-icon a-icon-text-separator" role="img" aria-label="|"></i>Style
         * 											name: With
         * 											Offer</a><i class="a-icon a-icon-text-separator" role="img" aria-label="|"></i><span class="a-declarative" data-action="reviews:filter-action:push-state" data-reviews:filter-action:push-state="{}"><a data-reftag="cm_cr_arp_d_rvw_rvwer" data-reviews-state-param="{&quot;pageNumber&quot;:&quot;1&quot;,&quot;reviewerType&quot;:&quot;avp_only_reviews&quot;}" class="a-link-normal" href="/OPPO-Fantastic-Purple-128GB-Storage/product-reviews/B08VB34KJ1/ref=cm_cr_arp_d_rvw_rvwer?ie=UTF8&amp;reviewerType=avp_only_reviews"><span data-hook="avp-badge" class="a-size-mini a-color-state a-text-bold">Verified Purchase</span></a></span>
         * 									</div>
         * 									<div class="a-row a-spacing-small review-data"><span data-hook="review-body" class="a-size-base review-text review-text-content">
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *     <span>
         *   Really awesome display 😍 super camera super design fabulous 😍💐
         * </span>
         *
         * 										</span></div>
         * 									<div class="a-popover-preload" id="a-popover-RYWF464PEZY3J_gallerySection_main">
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         * 										<div id="RYWF464PEZY3J_image_popover" data-hook="image-popover"
         * 											class="a-section cr-lightbox-popover-container">
         *
         *
         *
         *
         * 											<div class="cr-lightbox-image-viewer">
         * 												<div class="cr-lightbox-main-image-container">
         * 													<img alt="Customer image" src="https://images-na.ssl-images-amazon.com/images/G/01/x-locale/common/transparent-pixel._V192234675_.gif" class="cr-lightbox-main-image"/>
         *         </div>
         * 													<div
         * 														class="cr-lightbox-navigator-container cr-lightbox-navigator-container__back">
         * 														<div
         * 															class="cr-lightbox-navigator-button cr-lightbox-navigator-button__back">
         * 														</div>
         * 													</div>
         * 													<div
         * 														class="cr-lightbox-navigator-container cr-lightbox-navigator-container__next">
         * 														<div
         * 															class="cr-lightbox-navigator-button cr-lightbox-navigator-button__next">
         * 														</div>
         * 													</div>
         * 												</div>
         * 												<div class="a-section cr-lightbox-review-information">
         * 													<div class="a-section a-spacing-mini cr-review-stars-and-title">
         * 														<i class="a-icon a-icon-star a-star-5 cr-lightbox-review-rating"><span class="a-icon-alt">5.0 out of 5 stars</span></i>
         * 														<span class="a-size-base cr-lightbox-review-title a-text-bold">
         *                 Design and display 😍
         *             </span>
         * 														<br>
         * 														<span class="a-size-small a-color-secondary cr-lightbox-review-origin">
         *
         *
         *
         *
         *
         *                                 By Uma Dasari on 3 May 2021
         *
         *
         *
         *
         *
         *             </span>
         * 													</div>
         * 													<span class="a-size-base cr-lightbox-review-body">
         *             Really awesome display 😍 super camera super design fabulous 😍💐
         *         </span>
         * 													<div class="a-section a-spacing-top-base">
         * 														<span class="a-size-medium a-color-secondary">
         *                 Images in this review
         *             </span>
         * 														<div
         * 															class="a-section a-spacing-top-mini cr-lightbox-image-thumbnails">
         *
         * 															<img alt="Customer image" src="https://images-na.ssl-images-amazon.com/images/I/91bPrvvXk8L._SY88.jpg" class="cr-lightbox-image-thumbnail"/>
         *
         * 															<img alt="Customer image" src="https://images-na.ssl-images-amazon.com/images/I/91c0PtSjGqL._SY88.jpg" class="cr-lightbox-image-thumbnail"/>
         *
         * 															<img alt="Customer image" src="https://images-na.ssl-images-amazon.com/images/I/91xR0-GCsiL._SY88.jpg" class="cr-lightbox-image-thumbnail"/>
         *
         *             </div>
         * 														</div>
         * 													</div>
         *
         *
         * 												</div>
         *
         *
         *
         * 												<script>
         * 													function toggleSeeAllView() {
         *        P.when('A', 'cr-image-popover-controller').execute(function(A, imagePopoverController) {
         *           imagePopoverController.toggleSeeAllView(true);
         *        });
         *    }
         * 												</script>
         * 											</div>
         * 											<div id="RYWF464PEZY3J_imageSection_main"
         * 												class="a-section a-spacing-medium review-image-container">
         * 												<div class="review-image-tile-section" data-reviewid="RYWF464PEZY3J">
         * 													<span class="a-declarative" data-action="a-modal" data-a-modal="{&quot;name&quot;:&quot;RYWF464PEZY3J_gallerySection_main&quot;}" id="RYWF464PEZY3J-0"><a href="javascript:void(0)" class="a-popover-trigger a-declarative"><img alt="Customer image" src="https://images-na.ssl-images-amazon.com/images/I/91bPrvvXk8L._SY88.jpg" data-hook="review-image-tile" class="review-image-tile" height="88" width="100%"/><i class="a-icon a-icon-popover"></i></a></span><span class="a-declarative" data-action="a-modal" data-a-modal="{&quot;name&quot;:&quot;RYWF464PEZY3J_gallerySection_main&quot;}" id="RYWF464PEZY3J-1"><a href="javascript:void(0)" class="a-popover-trigger a-declarative"><img alt="Customer image" src="https://images-na.ssl-images-amazon.com/images/I/91c0PtSjGqL._SY88.jpg" data-hook="review-image-tile" class="review-image-tile" height="88" width="100%"/><i class="a-icon a-icon-popover"></i></a></span><span class="a-declarative" data-action="a-modal" data-a-modal="{&quot;name&quot;:&quot;RYWF464PEZY3J_gallerySection_main&quot;}" id="RYWF464PEZY3J-2"><a href="javascript:void(0)" class="a-popover-trigger a-declarative"><img alt="Customer image" src="https://images-na.ssl-images-amazon.com/images/I/91xR0-GCsiL._SY88.jpg" data-hook="review-image-tile" class="review-image-tile" height="88" width="100%"/><i class="a-icon a-icon-popover"></i></a></span>
         * 												</div>
         * 											</div>
         * 											<script>
         * 												P.when('A', 'cr-image-popover-controller').execute(function(A, imagePopoverController) {
         *           A.on("a:popover:beforeShow:RYWF464PEZY3J_gallerySection_main", function(data) {
         *             imagePopoverController.initImagePopover("RYWF464PEZY3J", "[https://images-na.ssl-images-amazon.com/images/I/91bPrvvXk8L._SL1600_.jpg, https://images-na.ssl-images-amazon.com/images/I/91c0PtSjGqL._SL1600_.jpg, https://images-na.ssl-images-amazon.com/images/I/91xR0-GCsiL._SL1600_.jpg]", data);
         *           });
         *         });
         * 											</script>
         * 											<div class="a-row review-comments comments-for-RYWF464PEZY3J">
         * 												<div data-reftag="cm_cr_arp_d_cmt_opn" aria-live="polite"
         * 													data-a-expander-name="review_comment_expander"
         * 													class="a-row a-expander-container a-expander-inline-container cr-vote-action-bar">
         * 													<span class="cr-vote" data-hook="review-voting-widget">
         *   <div class="a-row a-spacing-small"><span data-hook="helpful-vote-statement" class="a-size-base a-color-tertiary cr-vote-text">234 people found this helpful</span>
         * 												</div>
         * 												<div class="cr-helpful-button aok-float-left">
         * 													<span class="a-button a-button-base"><span class="a-button-inner"><a href="https://www.amazon.in/ap/signin?openid.return_to=https%3A%2F%2Fwww.amazon.in%2FOPPO-Fantastic-Purple-128GB-Storage%2Fproduct-reviews%2FB08VB34KJ1%2Fref%3Dcm_cr_arp_d_vote_lft%3Fie%3DUTF8%26reviewerType%3Dall_reviews%26csrfT%3DgjeDjtjbWhPQQyCQ%252FtzJrTqKuK4SSIJVrAUn6JAAAAABAAAAAGFo3aFyYXcAAAAA%252B4kUEk%252F7iMGR3xPcX6iU%26reviewId%3DRYWF464PEZY3J&amp;openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&amp;openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&amp;openid.assoc_handle=inflex&amp;openid.mode=checkid_setup&amp;openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0" data-hook="vote-helpful-button" class="a-button-text" role="button"><div class="cr-helpful-text">
         *           Helpful</div>
         *       </a></span></span></div>
         * 									</span><span class="cr-footer-line-height">
         *           <span><i class="a-icon a-icon-text-separator" role="img" aria-label="|"></i><span class="a-declarative" data-action="cr-popup" data-cr-popup="{&quot;width&quot;:&quot;580&quot;,&quot;title&quot;:&quot;ReportAbuse&quot;,&quot;url&quot;:&quot;/hz/reviews-render/report-review?ie=UTF8&amp;ref=cm_cr_arp_d_rvw_hlp&amp;csrfT=gjeDjtjbWhPQQyCQ%2FtzJrTqKuK4SSIJVrAUn6JAAAAABAAAAAGFo3aFyYXcAAAAA%2B4kUEk%2F7iMGR3xPcX6iU&amp;reviewId=RYWF464PEZY3J&quot;,&quot;height&quot;:&quot;380&quot;}"><a class="a-size-base a-link-normal a-color-secondary report-abuse-link a-text-normal" href="/hz/reviews-render/report-review?ie=UTF8&amp;ref=cm_cr_arp_d_rvw_hlp&amp;csrfT=gjeDjtjbWhPQQyCQ%2FtzJrTqKuK4SSIJVrAUn6JAAAAABAAAAAGFo3aFyYXcAAAAA%2B4kUEk%2F7iMGR3xPcX6iU&amp;reviewId=RYWF464PEZY3J">Report abuse</a></span></span></span>
         *
         * 									<div aria-expanded="false"
         * 										class="a-expander-content a-spacing-top-base a-spacing-large a-expander-inline-content a-expander-inner"
         * 										style="display:none">
         * 										<div class="a-row a-spacing-mini review-comments-header aok-hidden">
         * 											<ul class="a-viewoptions-list a-viewoptions-section a-span12">
         * 												<div class="a-row a-spacing-none a-grid-vertical-align a-grid-center">
         * 													<div class="a-column a-span6">
         * 														<span class="a-size-base a-viewoptions-list-label">Showing <span class='review-comment-count'>0</span>
         * 														comments</span></div>
         * 												</div>
         * 											</ul>
         * 										</div>
         * 										<div
         * 											class="a-section a-spacing-extra-large a-spacing-top-medium a-text-center comment-load-error aok-hidden">
         * 											<div class="a-box a-alert a-alert-error cr-error" aria-live="assertive"
         * 												role="alert">
         * 												<div class="a-box-inner a-alert-container">
         * 													<h4 class="a-alert-heading">There was a problem loading comments
         * 														right now. Please try again later.</h4>
         * 													<i class="a-icon a-icon-alert"></i>
         * 													<div class="a-alert-content"></div>
         * 												</div>
         * 											</div>
         * 										</div>
         * 										<div class="a-section a-spacing-none review-comments"></div>
         * 										<div
         * 											class="a-spinner-wrapper comment-loading aok-hidden a-spacing-top-medium a-spacing-extra-large">
         * 											<span class="a-spinner a-spinner-medium"></span></div>
         * 										<hr aria-hidden="true"
         * 											class="a-spacing-none a-spacing-top-large a-divider-normal" />
         * 									</div>
         * 								</div>
         * 							</div>
         * 						</div>
         * 					</div>
         *
         */
        Elements elements = doc.select("[data-hook='review']");

        for (Element element : elements) {
            Elements body = element.select("[data-hook='review-body']");
            Elements bodySpan = body.get(0).getElementsByTag("span");
            Element reviewTrueEle = bodySpan.get(1);
            String review = reviewTrueEle.wholeText();


            Elements star = element.select("[data-hook='review-star-rating']");


        }

        Elements urls = doc.select("li.a-last").select("a[href]");
        String nextUrl = urls.get(0).attr("href");

//        Elements pageActions = doc.select("[data-action='reviews:page-action']");
//        String nextUrl = pageActions.get(0).child(0).

        try {
            Document doc2 = Jsoup.connect("https://www.amazon.in"+nextUrl).get();
            Elements elements22 = doc2.select("[data-hook='review']");
            Elements urls2 = doc2.select("li.a-last").select("a[href]");
            String nextUrl2 = urls2.get(0).attr("href");
        } catch (IOException e) {
            e.printStackTrace();
        }

        String title = doc.title();
    }
}
