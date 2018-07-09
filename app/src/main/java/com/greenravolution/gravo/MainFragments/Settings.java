package com.greenravolution.gravo.MainFragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.greenravolution.gravo.R;
import com.greenravolution.gravo.contents.ActivitySettingsItems;
import com.greenravolution.gravo.contents.ActivityWebView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Settings extends Fragment {


    public Settings() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        RelativeLayout tnc = view.findViewById(R.id.tnc);
        RelativeLayout pp = view.findViewById(R.id.pp);
        RelativeLayout ag = view.findViewById(R.id.ag);
        ag.setOnClickListener(v->startActivity(new Intent(getContext(), ActivityWebView.class)));
        tnc.setOnClickListener(v-> startActivity(new Intent(getContext(), ActivitySettingsItems.class).putExtra("tnc","at green ravolution, we care about you and the trust you give us\n" +
                "Last updated [JUNE, 10, 2018]\n" +
                "\n" +
                "GREEN RAVOLUTION TERMS & CONDITIONS OF SERVICE\n" +
                "\n" +
                "INTRODUCTION\n" +
                "By accessing the Application (as defined below) and/or using the Service (as defined below), you agree that you have read, understood, accepted and agreed with the Terms and Conditions (“Terms”) of Service. You further agree to the representations made by yourself below. These Terms govern your access to and use of but not exclusive to, various websites, SMS, APIs, email notifications, applications, buttons, widgets, ads, commerce services, and our other services that link to these Terms (collectively, the “Service” or “Services”), and any information, data, text, graphics, images, listings, pricing, blogs, message boards, audio, videos, links, communication tools, or other materials uploaded, downloaded or appearing on the Services (collectively referred to as “Content”). \n" +
                "\n" +
                "\n" +
                "AGREEMENT TO TERMS\n" +
                "You may use the Services only if you agree to the Terms elaborated in the following paragraphs, and are not a person barred from receiving Services under the laws of Singapore.  These Terms constitute a legally binding agreement made between you, whether personally or on behalf of an entity (“you,” “your”) and Green Ravolution Pte Ltd (Company No. 201208536W)  (“Company,” “we,” “us” or “our”), concerning but not limited to your access to and use of GRAVO (“Application”) to facilitate Scheduling of Recycling Collection, Collection, Storage and Recycling/Destruction of your Data, Compensation (where applicable) for Recyclables Given Up, posting of or trading in Green products, monitoring of the progress of Green initiatives, well as any other media form, media channel, mobile website or website related, linked, or otherwise connected thereto (collectively, the “Media”). You agree that by accessing the Application, you have read, understood, and agree to be bound by all of the Terms.  IF YOU DO NOT AGREE WITH ANY OF THESE TERMS, YOU ARE ADVISED NOT TO USE THE APPLICATION OR DISCONTINUE ITS USE IMMEDIATELY.  \n" +
                "\n" +
                "Supplemental Terms or documents that may be posted on the Application from time to time are hereby expressly incorporated herein by reference. We reserve the right, in our sole discretion, to make changes or modifications to these Terms at any time and for any reason.  We will alert you about any changes by updating the “Last updated” date of these Terms and you waive any right to receive specific notice of each such change.  It is your responsibility to periodically review these Terms to stay informed of updates.  You will be subject to, and will be deemed to have been made aware of and to have accepted, the changes in any revised Terms by your continued use of the Application after the date when such revised Terms are posted.  \n" +
                "\n" +
                "The information provided on The Application is not intended for distribution to or use by any person or entity in any jurisdiction or country where such distribution or use would be contrary to law or regulation or which would subject us to any registration requirement within such jurisdiction or country. Accordingly, those persons who choose to access the Application from other locations do so on their own initiative and are solely responsible for compliance with local laws, if and to the extent local laws are applicable. \n" +
                "\n" +
                "[The Application is intended for users who are at least 13 years of age.] All users who are minors in the jurisdiction in which they reside (generally under the age of 18) must have the permission of, and be directly supervised by, their parent or guardian to use the Application. If you are a minor, you must have your parent or guardian read and agree to these Terms prior to you using the Application.  \n" +
                "\n" +
                "INTELLECTUAL PROPERTY RIGHTS\n" +
                "Unless otherwise indicated, the Application is the Company’s proprietary property and all source code, databases, functionality, software, website designs, mobile application designs,  audio, video, text, photographs, and graphics on the Application (collectively, the “Content”) and the trademarks, service marks, and logos contained therein (the “Marks”) are owned or controlled by the Company or licensed to the Company, and are protected by copyright and trademark laws and various other intellectual property rights and unfair competition laws of The Republic of Singapore, foreign jurisdictions, and international conventions.  The Content and the Marks are provided on the Application “AS IS” for your information and personal use only.  Except as expressly provided in these Terms, no part of the Application and no Content or Marks may be copied, reproduced, aggregated, republished, uploaded, posted, publicly displayed, encoded, translated, transmitted, distributed, sold, licensed, or otherwise exploited for any commercial purpose whatsoever, without our express prior written permission. \n" +
                "\n" +
                "Provided that you are eligible to use the Application, you are granted a limited license to access and use the Application and to download or print a copy of any portion of the Content to which you have properly gained access solely for your personal, non-commercial use. We reserve all rights not expressly granted to you in and to the Application, Content and Marks.\n" +
                "\n" +
                "USER REPRESENTATIONS\n" +
                "By using the Application, you represent and warrant that: [(1) all registration information you submit will be true, accurate, current, and complete; (2) you will maintain the accuracy of such information and promptly update such registration information as necessary;] (3) you have the legal capacity and you agree to comply with these Terms; (4) you are not a minor, or if a minor, you have received parental (or any such person authorized to grant) permission to use the Application]; (5) you will not access the Application through automated or non-human means, whether through a bot, script or otherwise; (6) you will not use the Application for any illegal or unauthorized purpose;  and (7) your use of the Application will not violate any applicable law or regulation.\n" +
                "\n" +
                "If you provide any information that is untrue, inaccurate, not current, or incomplete, we have the right to suspend or terminate your account and refuse any and all current or future use of the Application (or any portion thereof).  \n" +
                "\n" +
                "USER REGISTRATION\n" +
                "You will be required to register to use the Application. You agree to keep your password confidential and will be responsible for all use of your account and password. We reserve the right to remove, reclaim, or change a username you select if we determine, in our sole discretion, that such username is inappropriate, obscene, or otherwise objectionable.\n" +
                " \n" +
                "PROHIBITED ACTIVITIES\n" +
                "You may not access or use the Application for any purpose other than that for which we make the Application available. the Application may not be used in connection with any commercial endeavors except those that are specifically endorsed or approved by us. \n" +
                "\n" +
                "As a user of the Application, you agree not to:\n" +
                "\n" +
                "systematically retrieve data or other content from the Application to create or compile, directly or indirectly, a collection, compilation, database, or directory without written permission from us.\n" +
                "make any unauthorized use of the Application, including collecting usernames and/or email addresses of users by electronic or other means for the purpose of sending unsolicited email, or creating user accounts by automated means or under false pretenses.\n" +
                "use a buying agent or purchasing agent to sell or make purchases on the Application.\n" +
                "use the Application to advertise or offer to sell goods and services.\n" +
                "circumvent, disable, or otherwise interfere with security-related features of the Application, including features that prevent or restrict the use or copying of any Content or enforce limitations on the use of the Application and/or the Content contained therein.\n" +
                "engage in unauthorized framing of or linking to the Application.\n" +
                "trick, defraud, or mislead us and other users, especially in any attempt to learn sensitive account information such as user passwords;\n" +
                "make improper use of our support services or submit false reports of abuse or misconduct.\n" +
                "engage in any automated use of the system, such as using scripts to send comments or messages, or using any data mining, robots, or similar data gathering and extraction tools.\n" +
                "interfere with, disrupt, or create an undue burden on the Application or the networks or services connected to the Application.\n" +
                "attempt to impersonate another user or person or use the username of another user.\n" +
                "sell or otherwise transfer your profile.\n" +
                "use any information obtained from the Application in order to harass, abuse, or harm another person.\n" +
                "use the Application as part of any effort to compete with us or otherwise use the Application and/or the Content for any revenue-generating endeavor or commercial enterprise.\n" +
                "decipher, decompile, disassemble, or reverse engineer any of the software comprising or in any way making up a part of the Application.\n" +
                "attempt to bypass any measures of the Application designed to prevent or restrict access to the Application, or any portion of the Application.\n" +
                "harass, annoy, intimidate, or threaten any of our employees, recycling collectors or agents engaged in providing any portion of the Application and Services to you.\n" +
                "delete the copyright or other proprietary rights notice from any Content.\n" +
                "copy or adapt the Application’s software, including but not limited to Flash, PHP, HTML, JavaScript, or other code.\n" +
                "upload or transmit (or attempt to upload or to transmit) viruses, Trojan horses, or other material, including excessive use of capital letters and spamming (continuous posting of repetitive text), that interferes with any party’s uninterrupted use and enjoyment of the Application or modifies, impairs, disrupts, alters, or interferes with the use, features, functions, operation, or maintenance of the Application.\n" +
                "upload or transmit (or attempt to upload or to transmit) any material that acts as a passive or active information collection or transmission mechanism, including without limitation, clear graphics interchange formats (“gifs”), 1×1 pixels, web bugs, cookies, or other similar devices (sometimes referred to as “spyware” or “passive collection mechanisms” or “pcms”).\n" +
                "except as may be the result of standard search engine or Internet browser usage, use, launch, develop, or distribute any automated system, including without limitation, any spider, robot, cheat utility, scraper, or offline reader that accesses the Site, or using or launching any unauthorized script or other software.\n" +
                "disparage, tarnish, or otherwise harm, in our opinion, us and/or the Application, Agents and/or recycling collectors.\n" +
                "use the Application in a manner inconsistent with any applicable laws or regulations.\n" +
                "\n" +
                "USER GENERATED CONTRIBUTIONS\n" +
                "The Application may invite you to chat, contribute to, or participate in blogs, message boards, online forums, and other functionality, and may provide you with the opportunity to create, submit, post, display, transmit, perform, publish, distribute, or broadcast content and materials to us or on the Application, including but not limited to text, writings, video, audio, photographs, graphics, comments, suggestions, or personal information or other material (collectively, \"Contributions\"). Contributions may be viewable by other users of the Application and through a third-party.  As such, any Contributions you transmit may be treated as non-confidential and non-proprietary.  When you create or make available any Contributions, you thereby represent and warrant that:\n" +
                "\n" +
                "the creation, distribution, transmission, public display, or performance, and the accessing, downloading, or copying of your Contributions do not and will not infringe the proprietary rights, including but not limited to the copyright, patent, trademark, trade secret, or moral rights of any third party.\n" +
                "you are the creator and owner of or have the necessary licenses, rights, consents, releases, and permissions to use and to authorize us, the Application, and other users of the Application to use your Contributions in any manner contemplated by the Application and these Terms.\n" +
                "you have the written consent, release, and/or permission of each and every identifiable individual person in your Contributions to use the name or likeness of each and every such identifiable individual person to enable inclusion and use of your Contributions in any manner contemplated by the Application and these Terms.\n" +
                "your Contributions are not false, inaccurate, or misleading.\n" +
                "your Contributions are not unsolicited or unauthorized advertising, promotional materials, pyramid schemes, chain letters, spam, mass mailings, or other forms of solicitation.\n" +
                "your Contributions are not obscene, lewd, lascivious, filthy, violent, harassing, libelous, slanderous, or otherwise objectionable (as determined by us).\n" +
                "your Contributions do not ridicule, mock, disparage, intimidate, or abuse anyone.\n" +
                "your Contributions do not advocate the violent overthrow of any government or incite, encourage, or threaten physical harm against another.\n" +
                "your Contributions do not violate any applicable law, regulation, or rule.\n" +
                "your Contributions do not violate the privacy or publicity rights of any third party.\n" +
                "your Contributions do not contain any material that solicits personal information from anyone under the age of 18 or exploits people under the age of 18 in a sexual and/or violent manner.\n" +
                "your Contributions do not violate any state law concerning child pornography, or otherwise intended to protect the health or well-being of minors;\n" +
                "your Contributions do not include any offensive comments that are connected to race, national origin, gender, sexual preference, or physical handicap.\n" +
                "your Contributions do not otherwise violate, or link to material that violates, any provision of these Terms, or any applicable law or regulation.\n" +
                "\n" +
                "Any use of the Application in violation of the foregoing violates these Terms and may result in, among other things, termination or suspension of your rights to use the Application. \n" +
                "  \n" +
                "CONTRIBUTION LICENSE\n" +
                "By posting your Contributions to any part of the Application [or making Contributions accessible to the Application by linking your account from the Application to any of your social networking accounts], you automatically grant, and you represent and warrant that you have the right to grant, to us an unrestricted, unlimited, irrevocable, perpetual, non-exclusive, transferable, royalty-free, fully-paid, worldwide right, and license to host, use, copy, reproduce, disclose, sell, resell, publish, broadcast, retitle, archive, store, cache, publicly perform, publicly display, reformat, translate, transmit, excerpt (in whole or in part), and distribute such Contributions (including, without limitation, your image and voice) for any purpose, commercial, advertising, or otherwise, and to prepare derivative works of, or incorporate into other works, such Contributions, and grant and authorize sublicenses of the foregoing. The use and distribution may occur in any media formats and through any media channels. \n" +
                "\n" +
                "This license will apply to any form, media, or technology now known or hereafter developed, and includes our use of your name, company name, and franchise name, as applicable, and any of the trademarks, service marks, trade names, logos, and personal and commercial images you provide.  You waive all moral rights in your Contributions, and you warrant that moral rights have not otherwise been asserted in your Contributions. \n" +
                "\n" +
                "We do not assert any ownership over your Contributions.  You retain full ownership of all of your Contributions and any intellectual property rights or other proprietary rights associated with your Contributions.  We are not liable for any statements or representations in your Contributions provided by you in any area on the Application. You are solely responsible for your Contributions to the Application and you expressly agree to exonerate us from any and all responsibility and to refrain from any legal action against us regarding your Contributions.   \n" +
                "\n" +
                "We have the right, in our sole and absolute discretion, (1) to edit, redact, or otherwise change any Contributions; (2) to re-categorize any Contributions to place them in more appropriate locations on the Application; and (3) to pre-screen or delete any Contributions at any time and for any reason, without notice. We have no obligation to monitor your Contributions.  \n" +
                "   \n" +
                "GUIDELINES FOR REVIEWS\n" +
                "We may provide you areas on the Application to leave reviews or ratings. When posting a review, you must comply with the following criteria: (1) you should have firsthand experience with the person/entity being reviewed; (2) your reviews should not contain offensive profanity, or abusive, racist, offensive, or hate language; (3) your reviews should not contain discriminatory references based on religion, race, gender, national origin, age, marital status, sexual orientation, or disability; (4) your reviews should not contain references to illegal activity; (5) you should not be affiliated with competitors if posting negative reviews; (6) you should not make any conclusions as to the legality of conduct; (7) you may not post any false or misleading statements; and (8) you may not organize a campaign encouraging others to post reviews, whether positive or negative. \n" +
                "\n" +
                "We may accept, reject, or remove reviews in our sole discretion. We have absolutely no obligation to screen reviews or to delete reviews, even if anyone considers reviews objectionable or inaccurate.  Reviews are not endorsed by us, and do not necessarily represent our opinions or the views of any of our affiliates or partners.  We do not assume liability for any review or for any claims, liabilities, or losses resulting from any review. By posting a review, you hereby grant to us a perpetual, non-exclusive, worldwide, royalty-free, fully-paid, assignable, and sublicensable right and license to reproduce, modify, translate, transmit by any means, display, perform, and/or distribute all content relating to reviews.\n" +
                "   \n" +
                "MOBILE APPLICATION LICENSE\n" +
                "Use License\n" +
                "If you access the Application, then we grant you a revocable, non-exclusive, non-transferable, limited right to install and use the mobile application on wireless electronic devices owned or controlled by you, and to access and use the Application on such devices strictly in accordance with the Terms of this mobile application license contained in these Terms. You shall not: (1) decompile, reverse engineer, disassemble, attempt to derive the source code of, or decrypt the application; (2) make any modification, adaptation, improvement, enhancement, translation, or derivative work from the application; (3) violate any applicable laws, rules, or regulations in connection with your access or use of the application; (4) remove, alter, or obscure any proprietary notice (including any notice of copyright or trademark) posted by us or the licensors of the Application; (5) use the Application for any revenue generating endeavor, commercial enterprise, or other purpose for which it is not designed or intended; (6) make the Application available over a network or other environment permitting access or use by multiple devices or users at the same time; (7) use the Application for creating a product, service, or software that is, directly or indirectly, competitive with or in any way a substitute for the Application; (8) use the Application to send automated queries to any website or to send any unsolicited commercial e-mail; or (9) use any proprietary information or any of our interfaces or our other intellectual property in the design, development, manufacture, licensing, or distribution of any applications, accessories, or devices for use with the Application.\n" +
                "\n" +
                "Apple and Android Devices\n" +
                "The following Terms apply when you use a mobile application obtained from either the Apple Store or Google Play (each an “App Distributor”) to access the Site: (1) the license granted to you for Our Mobile Application is limited to a non-transferable license to use the Application on a device that utilizes the Apple iOS or Android operating systems, as applicable, and in accordance with the usage rules set forth in the applicable App Distributor’s terms of service; (2) we are responsible for providing any maintenance and support services with respect to The Mobile Application as specified in the Terms of this Mobile Application license contained in these Terms or as otherwise required under applicable law, and you acknowledge that each App Distributor has no obligation whatsoever to furnish any maintenance and support services with respect to The Mobile Application; (3) in the event of any failure of The Mobile Application to conform to any applicable warranty, you may notify the applicable App Distributor, and the App Distributor, in accordance with its terms and policies, may refund the purchase price, if any, paid for The Mobile Application, and to the maximum extent permitted by applicable law, the App Distributor will have no other warranty obligation whatsoever with respect to The Mobile Application; (4) you must comply with applicable third-party terms of agreement when using The Mobile Application, e.g., if you have a VoIP application, then you must not be in violation of their wireless data service agreement when using The Mobile Application; and (5) you acknowledge and agree that the App Distributors are third-party beneficiaries of the Terms in this Mobile Application license contained in these Terms, and that each App Distributor will have the right (and will be deemed to have accepted the right) to enforce the Terms in this Mobile Application license contained in these Terms against you as a third-party beneficiary thereof.    \n" +
                "  \n" +
                "SOCIAL MEDIA\n" +
                "As part of the functionality of the Application, you may link your account with online accounts you have with third-party service providers (each such account, a “Third-Party Account”) by either: (1) providing your Third-Party Account login information through the Application; or (2) allowing us to access your Third-Party Account, as is permitted under the applicable Terms that govern your use of each Third-Party Account. You represent and warrant that you are entitled to disclose your Third-Party Account login information to us and/or grant us access to your Third-Party Account, without breach by you of any of the Terms that govern your use of the applicable Third-Party Account, and without obligating us to pay any fees or making us subject to any usage limitations imposed by the third-party service provider of the Third-Party Account.  By granting us access to any Third-Party Accounts, you understand that (1) we may access, make available, and store (if applicable) any content that you have provided to and stored in your Third-Party Account (the “Social Network Content”) so that it is available on and through the Site via your account, including without limitation any friend lists and (2) we may submit to and receive from your Third-Party Account additional information to the extent you are notified when you link your account with the Third-Party Account.  Depending on the Third-Party Accounts you choose and subject to the privacy settings that you have set in such Third-Party Accounts, personally identifiable information that you post to your Third-Party Accounts may be available on and through your account on the Application. Please note that if a Third-Party Account or associated service becomes unavailable or our access to such Third-Party Account is terminated by the third-party service provider, then Social Network Content may no longer be available on and through the Application. You will have the ability to disable the connection between your account on the Application and your Third-Party Accounts at any time. PLEASE NOTE THAT YOUR RELATIONSHIP WITH THE THIRD-PARTY SERVICE PROVIDERS ASSOCIATED WITH YOUR THIRD-PARTY ACCOUNTS IS GOVERNED SOLELY BY YOUR AGREEMENT(S) WITH SUCH THIRD-PARTY SERVICE PROVIDERS.  We make no effort to review any Social Network Content for any purpose, including but not limited to, for accuracy, legality, or non-infringement, and we are not responsible for any Social Network Content. You acknowledge and agree that we may access your email address book associated with a Third-Party Account and your contacts list stored on your mobile device or tablet computer solely for purposes of identifying and informing you of those contacts who have also registered to use the Application. You can deactivate the connection between the Application and your Third-Party Account by contacting us using the contact information below or through your account settings (if applicable). We will attempt to delete any information stored on our servers that was obtained through such Third-Party Account, except the username and profile picture that become associated with your account.\n" +
                "\n" +
                "SUBMISSIONS\n" +
                "You acknowledge and agree that any questions, comments, suggestions, ideas, feedback, or other information regarding the Application (\"Submissions\") provided by you to us are non-confidential and shall become our sole property.  We shall own exclusive rights, including all intellectual property rights, and shall be entitled to the unrestricted use and dissemination of these Submissions for any lawful purpose, commercial or otherwise, without acknowledgment or compensation to you.  You hereby waive all moral rights to any such Submissions, and you hereby warrant that any such Submissions are original with you or that you have the right to submit such Submissions. You agree there shall be no recourse against us for any alleged or actual infringement or misappropriation of any proprietary right in your Submissions. \n" +
                "\n" +
                "THIRD-PARTY WEBSITES AND CONTENT\n" +
                "The Application may contain (or you may be sent via the Application) links to other websites (\"Third-Party Websites\") as well as articles, photographs, text, graphics, pictures, designs, music, sound, video, information, applications, software, and other content or items belonging to or originating from third parties (\"Third-Party Content\"). Such Third-Party Websites and Third-Party Content are not investigated, monitored, or checked for accuracy, appropriateness, or completeness by us, and we are not responsible for any Third-Party Websites accessed through the Application or any Third-Party Content posted on, available through, or installed from the Application, including the content, accuracy, offensiveness, opinions, reliability, privacy practices, or other policies of or contained in the Third-Party Websites or the Third-Party Content.  Inclusion of, linking to, or permitting the use or installation of any Third-Party Websites or any Third-Party Content does not imply approval or endorsement thereof by us.  If you decide to leave the Application and access the Third-Party Websites or to use or install any Third-Party Content, you do so at your own risk, and you should be aware these Terms no longer govern. You should review the applicable terms and policies, including privacy and data gathering practices, of any website to which you navigate from the Application or relating to any applications you use or install from the Application. Any purchases you make through Third-Party Websites will be through other websites and from other companies, and we take no responsibility whatsoever in relation to such purchases which are exclusively between you and the applicable third party.  You agree and acknowledge that we do not endorse the products or services offered on Third-Party Websites and you shall hold us harmless from any harm caused by your purchase of such products or services. Additionally, you shall hold us harmless from any losses sustained by you or harm caused to you relating to or resulting in any way from any Third-Party Content or any contact with Third-Party Websites. \n" +
                "\n" +
                "\n" +
                "APPLICATION MANAGEMENT\n" +
                "We reserve the right, but not the obligation, to: (1) monitor the Application for violations of these Terms; (2) take appropriate legal action against anyone who, in our sole discretion, violates the law or these Terms, including without limitation, reporting such user to law enforcement authorities; (3) in our sole discretion and without limitation, refuse, restrict access to, limit the availability of, or disable (to the extent technologically feasible) any of your Contributions or any portion thereof; (4) in our sole discretion and without limitation, notice, or liability, to remove from the Application or otherwise disable all files and content that are excessive in size or are in any way burdensome to our systems; and (5) otherwise manage the Application in a manner designed to protect our rights and property and to facilitate the proper functioning of the Site.\n" +
                "\n" +
                "PRIVACY POLICY\n" +
                "We care about data privacy and security. Please review our Privacy Policy [CLICK HERE]. By using the Application, you agree to be bound by our Privacy Policy, which is incorporated into these Terms.  Please be advised the Application is hosted in The Republic of Singapore.  If you access the Application from the European Union, North America, ASEAN or any other region of the world with laws or other requirements governing personal data collection, use, or disclosure that differ from applicable laws in The Republic of Singapore, then through your continued use of the Application or Services, you are transferring your data to The Republic of Singapore, and you expressly consent to have your data transferred to and processed in The Republic of Singapore. \n" +
                "  \n" +
                "SINGAPORE DIGITAL COPYRIGHT ACT 2004 NOTICE AND POLICY\n" +
                "Notifications\n" +
                "We respect the intellectual property rights of others.  If you believe that any material available on or through the Application infringes upon any copyright you own or control, please immediately notify our Designated Copyright Agent using the contact information provided below (a “Notification”).  A copy of your Notification will be sent to the person who posted or stored the material addressed in the Notification.  Please be advised that pursuant to Singapore law you may be held liable for damages if you make material misrepresentations in a Notification. Thus, if you are not sure that material located on or linked to by the Application infringes your copyright, you should consider first contacting an attorney.\n" +
                "\n" +
                "All Notifications should meet the requirements of Singapore Copyright Act 2004 and include the following information: (1) A physical or electronic signature of a person authorized to act on behalf of the owner of an exclusive right that is allegedly infringed; (2) identification of the copyrighted work claimed to have been infringed, or, if multiple copyrighted works on the Application are covered by the Notification, a representative list of such works on the Application; (3) identification of the material that is claimed to be infringing or to be the subject of infringing activity and that is to be removed or access to which is to be disabled, and information reasonably sufficient to permit us to locate the material; (4) information reasonably sufficient to permit us to contact the complaining party, such as an address, telephone number, and, if available, an email address at which the complaining party may be contacted; (5) a statement that the complaining party has a good faith belief that use of the material in the manner complained of is not authorized by the copyright owner, its agent, or the law; and (6)  a statement that the information in the notification is accurate, and under penalty of perjury, that the complaining party is authorized to act on behalf of the owner of an exclusive right that is allegedly infringed upon.\n" +
                "\n" +
                "\tCounter Notification\n" +
                "If you believe your own copyrighted material has been removed from the Application as a result of a mistake or misidentification, you may submit a written counter notification to [us/our Designated Copyright Agent] using the contact information provided below (a “Counter Notification”). To be an effective Counter Notification under the Singapore Digital Copyright Act 2004 your Counter Notification must include substantially the following: (1) identification of the material that has been removed or disabled and the location at which the material appeared before it was removed or disabled; (2) a statement that you will accept service of process from the party that filed the Notification or the party's agent; (3) your name, address, and telephone number; (4) a statement under penalty of perjury that you have a good faith belief that the material in question was removed or disabled as a result of a mistake or misidentification of the material to be removed or disabled; and (5) your physical or electronic signature.\n" +
                "\n" +
                "If you send us a valid, written Counter Notification meeting the requirements described above, we will restore your removed or disabled material, unless we first receive notice from the party filing the Notification informing us that such party has filed a court action to restrain you from engaging in infringing activity related to the material in question. Please note that if you materially misrepresent that the disabled or removed content was removed by mistake or misidentification, you may be liable for damages, including costs and attorney's fees. Filing a false Counter Notification constitutes perjury.\n" +
                "\n" +
                "Designated Copyright Agent\n" +
                "[Name]\n" +
                "Attn: Copyright Agent\n" +
                "[Address]\n" +
                "[City, State, Postal Code / Zip]\n" +
                "[email]\n" +
                "\n" +
                "TERMS AND TERMINATION\n" +
                "These Terms shall remain in full force and effect while you use the Application. WITHOUT LIMITING ANY OTHER PROVISION OF THESE TERMS OF USE, WE RESERVE THE RIGHT TO, IN OUR SOLE DISCRETION AND WITHOUT NOTICE OR LIABILITY, DENY ACCESS TO AND USE OF THE APPLICATION, TO ANY PERSON FOR ANY REASON OR FOR NO REASON, INCLUDING WITHOUT LIMITATION FOR BREACH OF ANY REPRESENTATION, WARRANTY, OR COVENANT CONTAINED IN THESE TERMS OF USE OR OF ANY APPLICABLE LAW OR REGULATION. WE MAY TERMINATE YOUR USE OR PARTICIPATION IN THE APPLICATION OR DELETE [YOUR ACCOUNT AND] ANY CONTENT OR INFORMATION THAT YOU POSTED AT ANY TIME, WITHOUT WARNING, IN OUR SOLE DISCRETION.  \n" +
                "\n" +
                "If we terminate or suspend your account for any reason, you are prohibited from registering and creating a new account under your name, a fake or borrowed name, or the name of any third party, even if you may be acting on behalf of the third party. In addition to terminating or suspending your account, we reserve the right to take appropriate legal action, including without limitation pursuing civil, criminal, and injunctive redress.\n" +
                "\n" +
                "MODIFICATIONS AND INTERRUPTIONS \n" +
                "We reserve the right to change, modify, or remove the contents of the Application at any time or for any reason at our sole discretion without notice. However, we have no obligation to update any information on our Application.  We also reserve the right to modify or discontinue all or part of the Application without notice at any time.  We will not be liable to you or any third party for any modification, price change, suspension, or discontinuance of the Application.  \n" +
                "\n" +
                "We cannot guarantee the Application will be available at all times.  We may experience hardware, software, or other problems or need to perform maintenance related to the Application, resulting in interruptions, delays and/or errors.  We reserve the right to change, revise, update, suspend, discontinue, or otherwise modify the Application at any time or for any reason without notice to you.  You agree that we have no liability whatsoever for any loss, damage, or inconvenience caused by your inability to access or use the Application during any downtime or discontinuance of the Application. Nothing in these Terms will be construed to obligate us to maintain and support the Application or to supply any corrections, updates, or releases in connection therewith.\n" +
                "\n" +
                "GOVERNING LAW \n" +
                "These Terms and your use of the Application are governed by and construed in accordance with the laws of The Republic of Singapore applicable to agreements made and to be entirely performed within The Republic of Singapore, without regard to its conflict of law principles.  \n" +
                "\n" +
                "CORRECTIONS\n" +
                "There may be information on the Application that contains typographical errors, inaccuracies, or omissions that may relate to the Site, including descriptions, pricing, availability, and various other information.  We reserve the right to correct any errors, inaccuracies, or omissions and to change or update the information on the Application at any time, without prior notice.\n" +
                "\n" +
                "DISCLAIMER\n" +
                "THE APPLICATION IS PROVIDED ON AN AS-IS AND AS-AVAILABLE BASIS.  YOU AGREE THAT YOUR USE OF THE APPLICATION SERVICES WILL BE AT YOUR SOLE RISK. TO THE FULLEST EXTENT PERMITTED BY LAW, WE DISCLAIM ALL WARRANTIES, EXPRESS OR IMPLIED, IN CONNECTION WITH THE SITE AND YOUR USE THEREOF, INCLUDING, WITHOUT LIMITATION, THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, AND NON-INFRINGEMENT. WE MAKE NO WARRANTIES OR REPRESENTATIONS ABOUT THE ACCURACY OR COMPLETENESS OF THE APPLICATION’S CONTENT OR THE CONTENT OF ANY WEBSITES LINKED TO THIS APPLICATION AND WE WILL ASSUME NO LIABILITY OR RESPONSIBILITY FOR ANY (1) ERRORS, MISTAKES, OR INACCURACIES OF CONTENT AND MATERIALS, (2) PERSONAL INJURY OR PROPERTY DAMAGE, OF ANY NATURE WHATSOEVER, RESULTING FROM YOUR ACCESS TO AND USE OF THE APPLICATION AND ITS SERVICES, (3) ANY UNAUTHORIZED ACCESS TO OR USE OF OUR SECURE SERVERS AND/OR ANY AND ALL PERSONAL INFORMATION AND/OR FINANCIAL INFORMATION STORED THEREIN, (4) ANY INTERRUPTION OR CESSATION OF TRANSMISSION TO OR FROM THE APPLICATION, (5) ANY BUGS, VIRUSES, TROJAN HORSES, OR THE LIKE WHICH MAY BE TRANSMITTED TO OR THROUGH THE APPLICATION BY ANY THIRD PARTY, AND/OR (6) ANY ERRORS OR OMISSIONS IN ANY CONTENT AND MATERIALS OR FOR ANY LOSS OR DAMAGE OF ANY KIND INCURRED AS A RESULT OF THE USE OF ANY CONTENT POSTED, TRANSMITTED, OR OTHERWISE MADE AVAILABLE VIA THE APPLICATION. WE DO NOT WARRANT, ENDORSE, GUARANTEE, OR ASSUME RESPONSIBILITY FOR ANY PRODUCT OR SERVICE ADVERTISED OR OFFERED BY A THIRD PARTY THROUGH THE APPLICATION, ANY HYPERLINKED WEBSITE, OR ANY WEBSITE OR MOBILE APPLICATION FEATURED IN ANY BANNER OR OTHER ADVERTISING, AND WE WILL NOT BE A PARTY TO OR IN ANY WAY BE RESPONSIBLE FOR MONITORING ANY TRANSACTION BETWEEN YOU AND ANY THIRD-PARTY PROVIDERS OF PRODUCTS OR SERVICES.  AS WITH THE PURCHASE OF A PRODUCT OR SERVICE THROUGH ANY MEDIUM OR IN ANY ENVIRONMENT, YOU SHOULD USE YOUR BEST JUDGMENT AND EXERCISE CAUTION WHERE APPROPRIATE.\n" +
                " \n" +
                "LIMITATIONS OF LIABILITY\n" +
                "IN NO EVENT WILL WE OR OUR DIRECTORS, EMPLOYEES, OR AGENTS BE LIABLE TO YOU OR ANY THIRD PARTY FOR ANY DIRECT, INDIRECT, CONSEQUENTIAL, EXEMPLARY, INCIDENTAL, SPECIAL, OR PUNITIVE DAMAGES, INCLUDING LOST PROFIT, LOST REVENUE, LOSS OF DATA, LOSS OF PROPERTY OR OTHER DAMAGES ARISING FROM YOUR USE OF THE APPLICATION, EVEN IF WE HAVE BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES. \n" +
                "\n" +
                "INDEMNIFICATION\n" +
                "You agree to defend, indemnify, and hold us harmless, including our subsidiaries, affiliates, and all of our respective officers, agents, partners, and employees, from and against any loss, damage, liability, claim, or demand, including reasonable attorneys’ fees and expenses, made by any third party due to or arising out of: (1) [your Contributions]; (2) use of the Application;  (3) breach of these Terms; (4) any breach of your representations and warranties set forth in these Terms; (5) your violation of the rights of a third party, including but not limited to intellectual property rights; or (6) any overt harmful act toward any other user of the Application with whom you connected via the Application. Notwithstanding the foregoing, we reserve the right, at your expense, to assume the exclusive defense and control of any matter for which you are required to indemnify us, and you agree to cooperate, at your expense, with our defense of such claims. We will use reasonable efforts to notify you of any such claim, action, or proceeding which is subject to this indemnification upon becoming aware of it. \n" +
                "  \n" +
                "USER DATA\n" +
                "We will maintain certain data that you transmit to the Application for the purpose of managing the Application and providing Services. Although we perform regular routine backups of data, you are solely responsible for all data that you transmit or that relates to any activity you have undertaken using the Application. You agree that we shall have no liability to you for any loss or corruption of any such data, and you hereby waive any right of action against us arising from any such loss or corruption of such data.\n" +
                "  \n" +
                "MISCELLANEOUS\n" +
                "These Terms and any policies or operating rules posted by us on the Application constitute the entire agreement and understanding between you and us. Our failure to exercise or enforce any right or provision of these Terms shall not operate as a waiver of such right or provision.  These Terms operate to the fullest extent permissible by law. We may assign any or all of our rights and obligations to others at any time.  We shall not be responsible or liable for any loss, damage, delay, or failure to act caused by any cause beyond our reasonable control.  If any provision or part of a provision of these Terms is determined to be unlawful, void, or unenforceable, that provision or part of the provision is deemed severable from these Terms and does not affect the validity and enforceability of any remaining provisions. There is no joint venture, partnership, employment or agency relationship created between you and us as a result of these Terms or use of the Site.  You agree that these Terms will not be construed against us by virtue of having drafted them. You hereby waive any and all defenses you may have based on the electronic form of these Terms and the lack of signing by the parties hereto to execute these Terms.").putExtra("type", "Terms and Conditions")));
        pp.setOnClickListener(v-> startActivity(new Intent(getContext(), ActivitySettingsItems.class).putExtra("tnc","GRAVO MOBILE APP PRIVACY POLICY\n" +
                "\n" +
                "[MOBILE APP]\n" +
                "Last updated [month day, year]\n" +
                "\n" +
                "Green Ravolution Pte Ltd (UEN 201208536W) (“we” or “us” or “our”) respects the privacy of our users (“user” or “you”). This Privacy Policy, using the Personal Data Protection Act PDPA 2012) as reference, explains how we collect, use and disclose your information when you visit our mobile application (the “Application”).   Please read this Privacy Policy carefully.  IF YOU DO NOT AGREE WITH THE TERMS OF THIS PRIVACY POLICY, PLEASE DO NOT USE THE APPLICATION. \n" +
                "\n" +
                "We reserve the right to make changes to this Privacy Policy at any time and for any reason.  We will alert you about any changes by updating the “Last updated” date of this Privacy Policy.  You are encouraged to periodically review this Privacy Policy to stay informed of updates. You will be deemed to have been made aware of, will be subject to, and will be deemed to have accepted the changes in any revised Privacy Policy by your continued use of the Application after the date such revised Privacy Policy is posted.  \n" +
                "\n" +
                "This Privacy Policy does not apply to the third-party online/mobile store from which you install the Application or make payments, including any in-game virtual items, which may also collect and use data about you.  We are not responsible for any of the data collected by any such third party. \n" +
                "COLLECTION OF YOUR INFORMATION\n" +
                "We may collect information about you in a variety of ways.  The information we may collect via the Application depends on the content and services you use, and includes:  \n" +
                "\n" +
                "Personal Data \n" +
                "Demographic and other personally identifiable information (such as your name and email address) that you voluntarily give to us when choosing to participate in various activities related to the Application, such as but not limited to collection scheduling, collection location, sharing recycling achievements, sending feedback, and responding to surveys.  If you choose to share data about yourself via your profile, online chat, or other interactive areas of the Application, please be advised that all data you disclose in these areas is public and your data will be accessible to anyone who accesses the Application.\n" +
                "\n" +
                "Derivative Data  \n" +
                "Information our servers automatically collect when you access the Application, such as your native actions that are integral to the Application, including recycling habits, recycling history, or past recycling locations, as well as other interactions with the Application and other users via server log files.  \n" +
                "\n" +
                "Financial Data \n" +
                "Financial information, such as data related to your payment method (e.g. valid credit card number, card brand, expiration date) that we may collect when you purchase, order, return, exchange, or request information about our services from the Application. [We store only very limited, if any, financial information that we collect. Otherwise, all financial information is stored by our payment processor, such as but not limited to [Amazon Payments,] [Authornize.Net,]  [Braintree Payments,] [Chargify,] [Dwolla,] [Google Checkout,] [Paypal,] [SafeCharge,] [Stripe,] [WePay,] [2Checkout,] [other], and you are encouraged to review their privacy policy and contact them directly for responses to your questions.\n" +
                "\n" +
                "Facebook Permissions  \n" +
                "The Application may by default access your Facebook basic account information, including your name, email, gender, birthday, current city, and profile picture URL, as well as other information that you choose to make public. We may also request access to other permissions related to your account, such as friends, check-ins, and likes, and you may choose to grant or deny us access to each individual permission. For more information regarding Facebook permissions, refer to the Facebook Permissions Reference page.\n" +
                "\n" +
                "Data from Social Networks  \n" +
                "User information from social networking sites, such as [Apple’s Game Center, Facebook, Google+ Instagram, Pinterest, Twitter], including your name, your social network username, location, gender, birth date, email address, profile picture, and public data for contacts, if you connect your account to such social networks. This information may also include the contact information of anyone you invite to use and/or join the Application.\n" +
                "\n" +
                "Geo-Location Information \n" +
                "We will request access or permission to and track location-based information from your mobile device, either continuously or while you are using the Application, to provide location-based services. This is necessary because our services to you require us to be present at your desired location. If you wish to change our access or permissions, you may do so in your device’s settings, however you may not be able to use the Application to its optimum capacity.\n" +
                "\n" +
                "Mobile Device Access \n" +
                "We may request access or permission to certain features from your mobile device, including your mobile device’s [bluetooth, calendar, camera, microphone, reminders, sensors, SMS messages, social media accounts, storage,] and other features. If you wish to change our access or permissions, you may do so in your device’s settings, however you may not be able to use the Application to its optimum capacity.\n" +
                " \n" +
                "\n" +
                "Mobile Device Data \n" +
                "Device information such as your mobile device ID number, model, and manufacturer, version of your operating system, phone number, country, location, and any other data you choose to provide.\n" +
                "\n" +
                "Push Notifications \n" +
                "We may request to send you push notifications regarding your account or the Application’s services. If you wish to opt-out from receiving these types of communications.\n" +
                "\n" +
                "Third-Party Data \n" +
                "Information to third parties, such as but not limited to name, address, telephone number and profile to facilitate in execution of our services to you.\n" +
                "\n" +
                "Data From Contests, Giveaways, and Surveys \n" +
                "Personal and other information you may provide when entering the loyalty program or giveaways and/or responding to surveys.\n" +
                "\n" +
                "Tracking Technologies\n" +
                "Cookies and Web Beacons\n" +
                "We may use cookies, web beacons, tracking pixels, and other tracking technologies on the Application to help customize the Application and improve your experience. When you access the Application, your personal information is not collected through the use of tracking technology. Most browsers are set to accept cookies by default. You can remove or reject cookies, but be aware that such action could affect the availability and functionality of the Application. You may not decline web beacons. However, they can be rendered ineffective by declining all cookies or by modifying your web browser’s settings to notify you each time a cookie is tendered, permitting you to accept or decline cookies on an individual basis.\n" +
                "\n" +
                "Website Analytics \n" +
                "We may also partner with selected third-party vendors[, such as [Adobe Analytics,] [Google Analytics,] [Heap Analytics,] [Mixpanel,] [Piwik,] and others], to allow tracking technologies and remarketing services on the Application through the use of first party cookies and third-party cookies, to, among other things, analyze and track users’ use of the Application, determine the popularity of certain content, and better understand online activity. By accessing the Application, you consent to the collection and use of your information by these third-party vendors. You are encouraged to review their privacy policy and contact them directly for responses to your questions. We do not transfer personal information to these third-party vendors. However, if you do not want any information to be collected and used by tracking technologies, you can install and/or update your settings for one of the following: \n" +
                "[Adobe Privacy Choices Page]\n" +
                "[Clicktale Opt-Out Feature]\n" +
                "[Crazy Egg Opt-Out Feature]\n" +
                "Digital Advertising Alliance Opt-Out Tool\n" +
                "[Flurry Analytics Yahoo Opt-Out Manager]\n" +
                "[Google Analytics Opt-Out Plugin]  \n" +
                "[Google Ads Settings Page]\n" +
                "[Inspectlet Opt-Out Cookie]\n" +
                "[Kissmetrics Opt-Out Feature]\n" +
                "[Mixpanel Opt-Out Cookie]\n" +
                "Network Advertising Initiative Opt-Out Tool \n" +
                "\n" +
                "You should be aware that getting a new computer, installing a new browser, upgrading an existing browser, or erasing or otherwise altering your browser’s cookies files may also clear certain opt-out cookies, plug-ins, or settings.\n" +
                "USE OF YOUR INFORMATION\n" +
                "Having accurate information about you permits us to provide you with a smooth, efficient, and customized experience. Specifically, we may use information collected about you via the Application to: \n" +
                "\n" +
                "Create and manage your account\n" +
                "Generate a personal profile about you to make future visits to the Application more personalized.\n" +
                "Confirm Age\n" +
                "Allow scheduling of recycling pick up\n" +
                "Identify and acknowledge top recyclers on the Application.\n" +
                "Identify precise recycling pick up location \n" +
                "Give financial compensation to users for recycling\n" +
                "Give points to users for loyalty program\n" +
                "Compile anonymous recycling statistical data and analysis for use internally or with third parties. \n" +
                "Contact you regarding your account\n" +
                "Increase the efficiency and operation of the Application.\n" +
                "Monitor and analyze usage and trends to improve your experience with the Application.\n" +
                "Notify you of updates to the Application.\n" +
                "Offer new products to recycle, services, mobile applications, and/or recommendations to you.\n" +
                "Perform other business activities as needed.\n" +
                "Prevent fraudulent transactions, monitor against theft, and protect against criminal activity.\n" +
                "Process payments and refunds.\n" +
                "Request feedback and contact you about your use of the Application. \n" +
                "Resolve disputes and troubleshoot problems.\n" +
                "Respond to product and customer service requests.\n" +
                "Send you a newsletter\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "DISCLOSURE OF YOUR INFORMATION\n" +
                "We may share information we have collected about you in certain situations. Your information may be disclosed as follows: \n" +
                "\n" +
                "By Law or to Protect Rights \n" +
                "If we believe the release of information about you is necessary to respond to legal process, to investigate or remedy potential violations of our policies, or to protect the rights, property, and safety of others, we may share your information as permitted or required by any applicable law, rule, or regulation.  This includes exchanging information with other entities for fraud protection and credit risk reduction.\n" +
                "\n" +
                "Third-Party Service Providers \n" +
                "We may share your information with third parties that perform services for us or on our behalf, including payment processing, data analysis, email delivery, hosting services, customer service, and marketing assistance.  \n" +
                "\n" +
                "Marketing Communications\n" +
                "With your consent, or with an opportunity for you to withdraw consent, we may share your information with third parties for CSR marketing purposes, as permitted by law.\n" +
                "\n" +
                "Interactions with Other Users \n" +
                "If you interact with other users of the Application, those users may see your name, profile photo, and descriptions of your activity, including sending invitations to other users, chatting with other users, liking posts, following blogs. \n" +
                "\n" +
                "Online Postings\n" +
                "When you post comments, contributions or other content to the Applications, your posts may be viewed by all users and may be publicly distributed outside the Application in perpetuity\n" +
                "\n" +
                "Affiliates \n" +
                "We may share your information with our affiliates, in which case we will require those affiliates to honor this Privacy Policy. Affiliates include our parent company and any subsidiaries, joint venture partners or other companies that we control or that are under common control with us.\n" +
                "\n" +
                "Business Partners \n" +
                "We may share your information with our business partners to offer you certain products, services or promotions. \n" +
                "\n" +
                "\n" +
                "Offer Wall  \n" +
                "the Application may display a third-party-hosted “offer wall.”  Such an offer wall allows third-party advertisers to offer vouchers, gifts, or other items to users in return for acceptance and completion of an advertisement offer.  Such an offer wall may appear in the Application and be displayed to you based on certain data, such as your geographic area or demographic information.  When you click on an offer wall, you will leave the Application.  A unique identifier, such as your user ID, will be shared with the offer wall provider in order to prevent fraud and properly credit your account.    \n" +
                "\n" +
                "Social Media Contacts  \n" +
                "If you connect to the Application through a social network, your contacts on the social network will see your name, profile photo, and descriptions of your activity.\n" +
                "\n" +
                "Other Third Parties\n" +
                "We may share your information with advertisers and investors for the purpose of conducting general business analysis. We may also share your information with such third parties for marketing purposes, as permitted by law. \n" +
                "\n" +
                "Sale or Bankruptcy \n" +
                "If we reorganize or sell all or a portion of our assets, undergo a merger, or are acquired by another entity, we may transfer your information to the successor entity.  If we go out of business or enter bankruptcy, your information would be an asset transferred or acquired by a third party.  You acknowledge that such transfers may occur and that the transferee may decline honor commitments we made in this Privacy Policy. \n" +
                "\n" +
                "We are not responsible for the actions of third parties with whom you share personal or sensitive data, and we have no authority to manage or control third-party solicitations.  If you no longer wish to receive correspondence, emails or other communications from third parties, you are responsible for contacting the third party directly. \n" +
                "\n" +
                "Third Party Websites\n" +
                "The Application may contain links to third-party websites and applications of interest, including advertisements and external services that are not affiliated with us. Once you have used these links to leave the Application, any information you provide to these third parties is not covered by this Privacy Policy, and we cannot guarantee the safety and privacy of your information. Before visiting and providing any information to any third-party websites, you should inform yourself of the privacy policies and practices (if any) of the third party responsible for that website, and should take those steps necessary to, in your discretion, protect the privacy of your information. We are not responsible for the content or privacy and security practices and policies of any third parties, including other sites, services or applications that may be linked to or from the Application.\n" +
                "SECURITY OF YOUR INFORMATION\n" +
                "We use administrative, technical, and physical security measures to help protect your personal information. While we have taken reasonable steps to secure your personal information, please be aware that, no security measures are perfect or impenetrable, and no method of data transmission can be guaranteed against any interception or other type of misuse.  Any information disclosed online is vulnerable to interception and misuse by unauthorized parties.\n" +
                "POLICY FOR CHILDREN\n" +
                "The Application is open to all of you who have a desire to play an active role in activities that contribute to environmental protection.  The application encourages children to participate in activities that promote environmental protection, such as but not limited to recycling material, recycling projects, education involving eco-friendly systems and processes and related activities, and may also include payments and/or rewards for the recyclables.  If you are below 13 years of age, you acknowledge that you have obtained permission from your parent(s) or legal guardian(s), to use the Application, and all its features, and any payments resulting thereof, do not constitute payments for any kind employment linked to the Company. \n" +
                "OPTIONS REGARDING YOUR INFORMATION\n" +
                "Account Information\n" +
                "You may at any time review or change the information in your account or terminate your account by:\n" +
                "Logging into your account settings and updating your account\n" +
                "Contacting us using the contact information provided below\n" +
                "[Other]\n" +
                "Upon your request to terminate your account, we will deactivate or delete your account and information from our active databases. However, some information may be retained in our files to prevent fraud, troubleshoot problems, assist with any investigations, enforce our Terms of Use and/or comply with legal requirements.\n" +
                "\n" +
                "Emails and Communications\n" +
                "If you no longer wish to receive correspondence, emails, or other communications from us, you may opt-out by:\n" +
                "Noting your preferences at the time you register your account with the Application\n" +
                "Logging into your account settings and updating your preferences.\n" +
                "Contacting us using the contact information provided below\n" +
                "If you no longer wish to receive correspondence, emails, or other communications from third parties, you are responsible for contacting the third party directly. \n" +
                "CONTACT US\n" +
                "If you have questions or comments about this Privacy Policy, please contact us at:\n" +
                "\n" +
                "Green Ravolution Pte Ltd\n" +
                "21 Jalan Resak, \n" +
                "Singapore, 808506\n" +
                "+65 8522 9494\n" +
                "recycling@greenravolution.com.sg                                                                                                                             \n").putExtra("type","Privacy Policy")));
        return view;
    }

}
