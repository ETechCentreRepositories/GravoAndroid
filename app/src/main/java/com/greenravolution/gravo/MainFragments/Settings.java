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
        ag.setOnClickListener(v->startActivity(new Intent(getContext(), ActivitySettingsItems.class).putExtra("tnc","<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "</head>" +
                "<body style='text-align:justify;'>" +
                "<h3>Green Ravolution aspires for a GREENER world. To that end, Green Ravolution embraces technology that brings together different, relevant stakeholders, to implement sustainability solutions that are more inclusive and collaborative. Every one of us needs to play our part, to REDUCE, REUSE and RECYCLE (3R). The more of us who come together and contribute our ideas and efforts, the more we can promote 3R awareness, assimilation and action.  For us, this is REAL 3R." +
                "</h3></body></html>").putExtra("type","About Gravo")));
        tnc.setOnClickListener(v-> startActivity(new Intent(getContext(), ActivitySettingsItems.class).putExtra("tnc","" +
                "\n" +
                "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">\n" +
                "<HTML>\n" +
                "<HEAD>\n" +
                "\t<META HTTP-EQUIV=\"CONTENT-TYPE\" CONTENT=\"text/html; charset=utf-8\">\n" +
                "\t<TITLE></TITLE>\n" +
                "</HEAD>\n" +
                "<BODY style='text-align:justify;' LANG=\'en-US\' BGCOLOR=\'#ffffff\' DIR=\'LTR\'>\n" +
                "<P STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<P ALIGN=CENTER STYLE=\"margin-bottom: 0in; line-height: 115%; page-break-inside: auto; page-break-after: auto\"><A NAME=\"_2cipo4yr3w5d\"></A>\n" +
                "<FONT SIZE=7><B><SPAN STYLE=\"font-variant: small-caps\"><FONT FACE=\"Arial, serif\"><FONT SIZE=5 STYLE=\"font-size: 20pt\">At\n" +
                "Green Ravolution, we care about you and the trust you give us</FONT></FONT></SPAN></B></FONT></P>\n" +
                "<P ALIGN=CENTER STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT COLOR=\"#999999\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">Last\n" +
                "updated [JUNE, 10, 2018]</FONT></FONT></FONT></P>\n" +
                "<P ALIGN=RIGHT STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<P ALIGN=CENTER STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><I><B>GREEN\n" +
                "RAVOLUTION TERMS &amp; CONDITIONS OF SERVICE</B></I></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<H1 CLASS=\"western\"><A NAME=\"_a7mwfgcrtsqn\"></A>INTRODUCTION</H1>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">By\n" +
                "accessing the Application (as defined below) and/or using the Service\n" +
                "(as defined below), you agree that you have read, understood,\n" +
                "accepted and agreed with the Terms and Conditions (“Terms”) of\n" +
                "Service. You further agree to the representations made by yourself\n" +
                "below. These Terms govern your access to and use of but not exclusive\n" +
                "to, various websites, SMS, APIs, email notifications, applications,\n" +
                "buttons, widgets, ads, commerce services, and our other services that\n" +
                "link to these Terms (collectively, the “Service” or “Services”),\n" +
                "and any information, data, text, graphics, images, listings, pricing,\n" +
                "blogs, message boards, audio, videos, links, communication tools, or\n" +
                "other materials uploaded, downloaded or appearing on the Services\n" +
                "(collectively referred to as “Content”). </FONT></FONT>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<P STYLE=\"margin-bottom: 0in\"><BR>\n" +
                "</P>\n" +
                "<H1 CLASS=\"western\">AGREEMENT TO TERMS</H1>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><A NAME=\"_Hlk516140378\"></A><A NAME=\"_Hlk515916315\"></A>\n" +
                "<FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">You\n" +
                "may use the Services only if you agree to the Terms elaborated in the\n" +
                "following paragraphs, and are not a person barred from receiving\n" +
                "Services under the laws of Singapore.  These Terms constitute a\n" +
                "legally binding agreement made between you, whether personally or on\n" +
                "behalf of an entity (“you,” “your”) and Green Ravolution Pte\n" +
                "Ltd </FONT></FONT><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\"><B>(Company\n" +
                "No. 201208536W)</B></FONT></FONT><B>&nbsp;</B><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">\n" +
                "(“Company,” “we,” “us” or “our”), concerning but not\n" +
                "limited to your access to and use of GRAVO (“Application”) to\n" +
                "facilitate Scheduling of Recycling Collection, Collection, Storage\n" +
                "and Recycling/Destruction of your Data, Compensation (where\n" +
                "applicable) for Recyclables Given Up</FONT></FONT><FONT SIZE=1 STYLE=\"font-size: 8pt\">,\n" +
                "</FONT><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">posting\n" +
                "of or trading in Green products, monitoring of the progress of Green\n" +
                "initiatives, well as any other media form, media channel, mobile\n" +
                "website or website related, linked, or otherwise connected thereto\n" +
                "(collectively, the “Media”). You agree that by accessing the\n" +
                "Application, you have read, understood, and agree to be bound by all\n" +
                "of the Terms.  IF YOU DO NOT AGREE WITH ANY OF THESE TERMS, YOU ARE\n" +
                "ADVISED NOT TO USE THE APPLICATION OR DISCONTINUE ITS USE\n" +
                "IMMEDIATELY.  </FONT></FONT>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">Supplemental\n" +
                "Terms or documents that may be posted on the Application from time to\n" +
                "time are hereby expressly incorporated herein by reference. We\n" +
                "reserve the right, in our sole discretion, to make changes or\n" +
                "modifications to these Terms at any time and for any reason.  We will\n" +
                "alert you about any changes by updating the “Last updated” date\n" +
                "of these Terms and you waive any right to receive specific notice of\n" +
                "each such change.  It is your responsibility to periodically review\n" +
                "these Terms to stay informed of updates.  You will be subject to, and\n" +
                "will be deemed to have been made aware of and to have accepted, the\n" +
                "changes in any revised Terms by your continued use of the Application\n" +
                "after the date when such revised Terms are posted.  </FONT></FONT>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">The\n" +
                "information provided on The Application is not intended for\n" +
                "distribution to or use by any person or entity in any jurisdiction or\n" +
                "country where such distribution or use would be contrary to law or\n" +
                "regulation or which would subject us to any registration requirement\n" +
                "within such jurisdiction or country. Accordingly, those persons who\n" +
                "choose to access the Application from other locations do so on their\n" +
                "own initiative and are solely responsible for compliance with local\n" +
                "laws, if and to the extent local laws are applicable.&nbsp;</FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">[The\n" +
                "Application is intended for users who are at least 13 years of age.]\n" +
                "All users who are minors in the jurisdiction in which they reside\n" +
                "(generally under the age of 18) must have the permission of, and be\n" +
                "directly supervised by, their parent or guardian to use the\n" +
                "Application. If you are a minor, you must have your parent or\n" +
                "guardian read and agree to these Terms prior to you using the\n" +
                "Application.  </FONT></FONT>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<H1 CLASS=\"western\"><A NAME=\"_4rd71iod99ud\"></A>INTELLECTUAL PROPERTY\n" +
                "RIGHTS</H1>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">Unless\n" +
                "otherwise indicated, the Application is the Company’s proprietary\n" +
                "property and all source code, databases, functionality, software,\n" +
                "website designs, mobile application designs,  audio, video, text,\n" +
                "photographs, and graphics on the Application (collectively, the\n" +
                "“Content”) and the trademarks, service marks, and logos contained\n" +
                "therein (the “Marks”) are owned or controlled by the Company or\n" +
                "licensed to the Company, and are protected by copyright and trademark\n" +
                "laws and various other intellectual property rights and unfair\n" +
                "competition laws of The Republic of Singapore, foreign jurisdictions,\n" +
                "and international conventions.  The Content and the Marks are\n" +
                "provided on the Application “AS IS” for your information and\n" +
                "personal use only.  Except as expressly provided in these Terms, no\n" +
                "part of the Application and no Content or Marks may be copied,\n" +
                "reproduced, aggregated, republished, uploaded, posted, publicly\n" +
                "displayed, encoded, translated, transmitted, distributed, sold,\n" +
                "licensed, or otherwise exploited for any commercial purpose\n" +
                "whatsoever, without our express prior written permission. </FONT></FONT>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">Provided\n" +
                "that you are eligible to use the Application, you are granted a\n" +
                "limited license to access and use the Application and to download or\n" +
                "print a copy of any portion of the Content to which you have properly\n" +
                "gained access solely for your personal, non-commercial use. We\n" +
                "reserve all rights not expressly granted to you in and to the\n" +
                "Application, Content and Marks.</FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<H1 CLASS=\"western\"><A NAME=\"_vhkegautf00d\"></A>USER REPRESENTATIONS</H1>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">By\n" +
                "using the Application, you represent and warrant that: [(1) all\n" +
                "registration information you submit will be true, accurate, current,\n" +
                "and complete;</FONT></FONT><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\"><B>\n" +
                "</B></FONT></FONT><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">(2)\n" +
                "you will maintain the accuracy of such information and promptly\n" +
                "update such registration information as necessary;]</FONT></FONT><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\"><B>\n" +
                "</B></FONT></FONT><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">(3)\n" +
                "you have the legal capacity and you agree to comply with these Terms;\n" +
                "(4) you are not a minor, or if a minor, you have received parental\n" +
                "(or any such person authorized to grant) permission to use the\n" +
                "Application]; (5) you will not access the Application through\n" +
                "automated or non-human means, whether through a bot, script or\n" +
                "otherwise; (6) you will not use the Application for any illegal or\n" +
                "unauthorized purpose;  and (7) your use of the Application will not\n" +
                "violate any applicable law or regulation.</FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">If\n" +
                "you provide any information that is untrue, inaccurate, not current,\n" +
                "or incomplete, we have the right to suspend or terminate your account\n" +
                "and refuse any and all current or future use of the Application (or\n" +
                "any portion thereof).&nbsp; </FONT></FONT>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<H1 CLASS=\"western\"><A NAME=\"_esuoutkhaf53\"></A>USER REGISTRATION</H1>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">You\n" +
                "will be required to register to use the Application. You agree to\n" +
                "keep your password confidential and will be responsible for all use\n" +
                "of your account and password. We reserve the right to remove,\n" +
                "reclaim, or change a username you select if we determine, in our sole\n" +
                "discretion, that such username is inappropriate, obscene, or\n" +
                "otherwise objectionable.</FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\">&nbsp;</P>\n" +
                "<H1 CLASS=\"western\"><A NAME=\"_1voziltdxegg\"></A>PROHIBITED ACTIVITIES</H1>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">You\n" +
                "may not access or use the Application for any purpose other than that\n" +
                "for which we make the Application available. the Application may not\n" +
                "be used in connection with any commercial endeavors except those that\n" +
                "are specifically endorsed or approved by us. </FONT></FONT>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">As\n" +
                "a user of the Application, you agree not to:</FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<OL>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">systematically\n" +
                "\tretrieve data or other content from the Application to create or\n" +
                "\tcompile, directly or indirectly, a collection, compilation,\n" +
                "\tdatabase, or directory without written permission from us.</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">make\n" +
                "\tany unauthorized use of the Application, including collecting\n" +
                "\tusernames and/or email addresses of users by electronic or other\n" +
                "\tmeans for the purpose of sending unsolicited email, or creating user\n" +
                "\taccounts by automated means or under false pretenses.</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">use\n" +
                "\ta buying agent or purchasing agent to sell or make purchases on the\n" +
                "\tApplication.</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">use\n" +
                "\tthe Application to advertise or offer to sell goods and services.</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">circumvent,\n" +
                "\tdisable, or otherwise interfere with security-related features of\n" +
                "\tthe Application, including features that prevent or restrict the use\n" +
                "\tor copying of any Content or enforce limitations on the use of the\n" +
                "\tApplication and/or the Content contained therein.</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">engage\n" +
                "\tin unauthorized framing of or linking to the Application.</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">trick,\n" +
                "\tdefraud, or mislead us and other users, especially in any attempt to\n" +
                "\tlearn sensitive account information such as user passwords;</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">make\n" +
                "\timproper use of our support services or submit false reports of\n" +
                "\tabuse or misconduct.</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">engage\n" +
                "\tin any automated use of the system, such as using scripts to send\n" +
                "\tcomments or messages, or using any data mining, robots, or similar\n" +
                "\tdata gathering and extraction tools.</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">interfere\n" +
                "\twith, disrupt, or create an undue burden on the Application or the\n" +
                "\tnetworks or services connected to the Application.</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">attempt\n" +
                "\tto impersonate another user or person or use the username of another\n" +
                "\tuser.</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">sell\n" +
                "\tor otherwise transfer your profile.</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">use\n" +
                "\tany information obtained from the Application in order to harass,\n" +
                "\tabuse, or harm another person.</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">use\n" +
                "\tthe Application as part of any effort to compete with us or\n" +
                "\totherwise use the Application and/or the Content for any\n" +
                "\trevenue-generating endeavor or commercial enterprise.</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">decipher,\n" +
                "\tdecompile, disassemble, or reverse engineer any of the software\n" +
                "\tcomprising or in any way making up a part of the Application.</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">attempt\n" +
                "\tto bypass any measures of the Application designed to prevent or\n" +
                "\trestrict access to the Application, or any portion of the\n" +
                "\tApplication.</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">harass,\n" +
                "\tannoy, intimidate, or threaten any of our employees, recycling\n" +
                "\tcollectors or agents engaged in providing any portion of the\n" +
                "\tApplication and Services to you.</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">delete\n" +
                "\tthe copyright or other proprietary rights notice from any Content.</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">copy\n" +
                "\tor adapt the Application’s software, including but not limited \u0004to\n" +
                "\tFlash, PHP, HTML, JavaScript, or other code.\u0005<!-- Bryan to insert necessary information --></FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">upload\n" +
                "\tor transmit (or attempt to upload or to transmit) viruses, Trojan\n" +
                "\thorses, or other material, including excessive use of capital\n" +
                "\tletters and spamming (continuous posting of repetitive text), that\n" +
                "\tinterferes with any party’s uninterrupted use and enjoyment of the\n" +
                "\tApplication or modifies, impairs, disrupts, alters, or interferes\n" +
                "\twith the use, features, functions, operation, or maintenance of the\n" +
                "\tApplication.</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">upload\n" +
                "\tor transmit (or attempt to upload or to transmit) any material that\n" +
                "\tacts as a passive or active information collection or transmission\n" +
                "\tmechanism, including without limitation, clear graphics interchange\n" +
                "\tformats (“gifs”), 1×1 pixels, web bugs, cookies, or other\n" +
                "\tsimilar devices (sometimes referred to as “spyware” or “passive\n" +
                "\tcollection mechanisms” or “pcms”).</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">except\n" +
                "\tas may be the result of \u0004standard search engine or Internet browser\n" +
                "\tusage\u0005<!-- Can we use this language for the app? Perhaps a question for Jon and team. -->,\n" +
                "\tuse, launch, develop, or distribute any automated system, including\n" +
                "\twithout limitation, any spider, robot, cheat utility, scraper, or\n" +
                "\toffline reader that accesses the Site, or using or launching any\n" +
                "\tunauthorized script or other software.</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">disparage,\n" +
                "\ttarnish, or otherwise harm, in our opinion, us and/or the\n" +
                "\tApplication, Agents and/or recycling collectors.</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">use\n" +
                "\tthe Application in a manner inconsistent with any applicable laws or\n" +
                "\tregulations.</FONT></FONT></P>\n" +
                "</OL>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=4 STYLE=\"font-size: 16pt\"><B>USER\n" +
                "GENERATED CONTRIBUTIONS</B></FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">The\n" +
                "Application may invite you to chat, contribute to, or participate in\n" +
                "blogs, message boards, online forums, and other functionality, and\n" +
                "may provide you with the opportunity to create, submit, post,\n" +
                "display, transmit, perform, publish, distribute, or broadcast content\n" +
                "and materials to us or on the Application, including but not limited\n" +
                "to text, writings, video, audio, photographs, graphics, comments,\n" +
                "suggestions, or personal information or other material (collectively,\n" +
                "&quot;Contributions&quot;). Contributions may be viewable by other\n" +
                "users of the Application and through a third-party.  As such, any\n" +
                "Contributions you transmit may be treated as non-confidential and\n" +
                "non-proprietary.  When you create or make available any\n" +
                "Contributions, you thereby represent and warrant that:</FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<OL>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">the\n" +
                "\tcreation, distribution, transmission, public display, or\n" +
                "\tperformance, and the accessing, downloading, or copying of your\n" +
                "\tContributions do not and will not infringe the proprietary rights,\n" +
                "\tincluding but not limited to the copyright, patent, trademark, trade\n" +
                "\tsecret, or moral rights of any third party.</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">you\n" +
                "\tare the creator and owner of or have the necessary licenses, rights,\n" +
                "\tconsents, releases, and permissions to use and to authorize us, the\n" +
                "\tApplication, and other users of the Application to use your\n" +
                "\tContributions in any manner contemplated by the Application and\n" +
                "\tthese Terms.</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">you\n" +
                "\thave the written consent, release, and/or permission of each and\n" +
                "\tevery identifiable individual person in your Contributions to use\n" +
                "\tthe name or likeness of each and every such identifiable individual\n" +
                "\tperson to enable inclusion and use of your Contributions in any\n" +
                "\tmanner contemplated by the Application and these Terms.</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">your\n" +
                "\tContributions are not false, inaccurate, or misleading.</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">your\n" +
                "\tContributions are not unsolicited or unauthorized advertising,\n" +
                "\tpromotional materials, pyramid schemes, chain letters, spam, mass\n" +
                "\tmailings, or other forms of solicitation.</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">your\n" +
                "\tContributions are not obscene, lewd, lascivious, filthy, violent,\n" +
                "\tharassing, libelous, slanderous, or otherwise objectionable (as\n" +
                "\tdetermined by us).</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">your\n" +
                "\tContributions do not ridicule, mock, disparage, intimidate, or abuse\n" +
                "\tanyone.</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">your\n" +
                "\tContributions do not advocate the violent overthrow of any\n" +
                "\tgovernment or incite, encourage, or threaten physical harm against\n" +
                "\tanother.</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">your\n" +
                "\tContributions do not violate any applicable law, regulation, or\n" +
                "\trule.</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">your\n" +
                "\tContributions do not violate the privacy or publicity rights of any\n" +
                "\tthird party.</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">your\n" +
                "\tContributions do not contain any material that solicits personal\n" +
                "\tinformation from anyone under the age of 18 or exploits people under\n" +
                "\tthe age of 18 in a sexual and/or violent manner.</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">your\n" +
                "\tContributions do not violate any state law concerning child\n" +
                "\tpornography, or otherwise intended to protect the health or\n" +
                "\twell-being of minors;</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">your\n" +
                "\tContributions do not include any offensive comments that are\n" +
                "\tconnected to race, national origin, gender, sexual preference, or\n" +
                "\tphysical handicap.</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">your\n" +
                "\tContributions do not otherwise violate, or link to material that\n" +
                "\tviolates, any provision of these Terms, or any applicable law or\n" +
                "\tregulation.</FONT></FONT></P>\n" +
                "</OL>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-left: 0.3in; text-indent: -0.3in; margin-bottom: 0in; line-height: 115%\">\n" +
                "<BR>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">Any\n" +
                "use of the Application in violation of the foregoing violates these\n" +
                "Terms and may result in, among other things, termination or\n" +
                "suspension of your rights to use the Application. </FONT></FONT>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\">  \n" +
                "</P>\n" +
                "<H1 CLASS=\"western\"><A NAME=\"_osdumdez3bsf\"></A>CONTRIBUTION LICENSE</H1>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">By\n" +
                "posting your Contributions to any part of the Application [or making\n" +
                "Contributions accessible to the Application by linking your account\n" +
                "from the Application to any of your social networking accounts], you\n" +
                "automatically grant, and you represent and warrant that you have the\n" +
                "right to grant, to us an unrestricted, unlimited, irrevocable,\n" +
                "perpetual, non-exclusive, transferable, royalty-free, fully-paid,\n" +
                "worldwide right, and license to host, use, copy, reproduce, disclose,\n" +
                "sell, resell, publish, broadcast, retitle, archive, store, cache,\n" +
                "publicly perform, publicly display, reformat, translate, transmit,\n" +
                "excerpt (in whole or in part), and distribute such Contributions\n" +
                "(including, without limitation, your image and voice) for any\n" +
                "purpose, commercial, advertising, or otherwise, and to prepare\n" +
                "derivative works of, or incorporate into other works, such\n" +
                "Contributions, and grant and authorize sublicenses of the foregoing.\n" +
                "The use and distribution may occur in any media formats and through\n" +
                "any media channels. </FONT></FONT>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">This\n" +
                "license will apply to any form, media, or technology now known or\n" +
                "hereafter developed, and includes our use of your name, company name,\n" +
                "and franchise name, as applicable, and any of the trademarks, service\n" +
                "marks, trade names, logos, and personal and commercial images you\n" +
                "provide.  You waive all moral rights in your Contributions, and you\n" +
                "warrant that moral rights have not otherwise been asserted in your\n" +
                "Contributions. </FONT></FONT>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">We\n" +
                "do not assert any ownership over your Contributions.  You retain full\n" +
                "ownership of all of your Contributions and any intellectual property\n" +
                "rights or other proprietary rights associated with your\n" +
                "Contributions.  We are not liable for any statements or\n" +
                "representations in your Contributions provided by you in any area on\n" +
                "the Application. You are solely responsible for your Contributions to\n" +
                "the Application and you expressly agree to exonerate us from any and\n" +
                "all responsibility and to refrain from any legal action against us\n" +
                "regarding your Contributions.   </FONT></FONT>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">We\n" +
                "have the right, in our sole and absolute discretion, (1) to edit,\n" +
                "redact, or otherwise change any Contributions; (2) to re-categorize\n" +
                "any Contributions to place them in more appropriate locations on the\n" +
                "Application; and (3) to pre-screen or delete any Contributions at any\n" +
                "time and for any reason, without notice. We have no obligation to\n" +
                "monitor your Contributions.  </FONT></FONT>\n" +
                "</P>\n" +
                "<P STYLE=\"margin-bottom: 0in; line-height: 115%\">&nbsp;&nbsp;&nbsp;</P>\n" +
                "<H1 CLASS=\"western\"><A NAME=\"_azashdr7t4si\"></A>GUIDELINES FOR\n" +
                "REVIEWS</H1>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">We\n" +
                "may provide you areas on the Application to leave reviews or ratings.\n" +
                "When posting a review, you must comply with the following criteria:\n" +
                "(1) you should have firsthand experience with the person/entity being\n" +
                "reviewed; (2) your reviews should not contain offensive profanity, or\n" +
                "abusive, racist, offensive, or hate language; (3) your reviews should\n" +
                "not contain discriminatory references based on religion, race,\n" +
                "gender, national origin, age, marital status, sexual orientation, or\n" +
                "disability; (4) your reviews should not contain references to illegal\n" +
                "activity; (5) you should not be affiliated with competitors if\n" +
                "posting negative reviews; (6) you should not make any conclusions as\n" +
                "to the legality of conduct; (7) you may not post any false or\n" +
                "misleading statements; and (8) you may not organize a campaign\n" +
                "encouraging others to post reviews, whether positive or negative. </FONT></FONT>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">We\n" +
                "may accept, reject, or remove reviews in our sole discretion. We have\n" +
                "absolutely no obligation to screen reviews or to delete reviews, even\n" +
                "if anyone considers reviews objectionable or inaccurate.  Reviews are\n" +
                "not endorsed by us, and do not necessarily represent our opinions or\n" +
                "the views of any of our affiliates or partners.  We do not assume\n" +
                "liability for any review or for any claims, liabilities, or losses\n" +
                "resulting from any review. By posting a review, you hereby grant to\n" +
                "us a perpetual, non-exclusive, worldwide, royalty-free, fully-paid,\n" +
                "assignable, and sublicensable right and license to reproduce, modify,\n" +
                "translate, transmit by any means, display, perform, and/or distribute\n" +
                "all content relating to reviews.</FONT></FONT></P>\n" +
                "<P STYLE=\"margin-bottom: 0in; line-height: 115%\">&nbsp;&nbsp;&nbsp;</P>\n" +
                "<H1 CLASS=\"western\"><A NAME=\"_9e8l9fhxyyva\"></A>MOBILE APPLICATION\n" +
                "LICENSE</H1>\n" +
                "<H2 CLASS=\"western\"><A NAME=\"_apvbotjtf8l9\"></A>Use License</H2>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">If\n" +
                "you access the Application, then we grant you a revocable,\n" +
                "non-exclusive, non-transferable, limited right to install and use the\n" +
                "mobile application on wireless electronic devices owned or controlled\n" +
                "by you, and to access and use the Application on such devices\n" +
                "strictly in accordance with the Terms of this mobile application\n" +
                "license contained in these Terms. You shall not: (1) decompile,\n" +
                "reverse engineer, disassemble, attempt to derive the source code of,\n" +
                "or decrypt the application; (2) make any modification, adaptation,\n" +
                "improvement, enhancement, translation, or derivative work from the\n" +
                "application; (3) violate any applicable laws, rules, or regulations\n" +
                "in connection with your access or use of the application; (4) remove,\n" +
                "alter, or obscure any proprietary notice (including any notice of\n" +
                "copyright or trademark) posted by us or the licensors of the\n" +
                "Application; (5) use the Application for any revenue generating\n" +
                "endeavor, commercial enterprise, or other purpose for which it is not\n" +
                "designed or intended; (6) make the Application available over a\n" +
                "network or other environment permitting access or use by multiple\n" +
                "devices or users at the same time; (7) use the Application for\n" +
                "creating a product, service, or software that is, directly or\n" +
                "indirectly, competitive with or in any way a substitute for the\n" +
                "Application; (8) use the Application to send automated queries to any\n" +
                "website or to send any unsolicited commercial e-mail; or (9) use any\n" +
                "proprietary information or any of our interfaces or our other\n" +
                "intellectual property in the design, development, manufacture,\n" +
                "licensing, or distribution of any applications, accessories, or\n" +
                "devices for use with the Application.</FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<H2 CLASS=\"western\"><A NAME=\"_29udqsku4ybv\"></A>Apple and Android\n" +
                "Devices</H2>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">The\n" +
                "following Terms apply when you use a mobile application obtained from\n" +
                "either the Apple Store or Google Play (each an “App Distributor”)\n" +
                "to access the Site: (1) the license granted to you for Our Mobile\n" +
                "Application is limited to a non-transferable license to use the\n" +
                "Application on a device that utilizes the Apple iOS or Android\n" +
                "operating systems, as applicable, and in accordance with the usage\n" +
                "rules set forth in the applicable App Distributor’s terms of\n" +
                "service; (2) we are responsible for providing any maintenance and\n" +
                "support services with respect to The Mobile Application as specified\n" +
                "in the Terms of this Mobile Application license contained in these\n" +
                "Terms or as otherwise required under applicable law, and you\n" +
                "acknowledge that each App Distributor has no obligation whatsoever to\n" +
                "furnish any maintenance and support services with respect to The\n" +
                "Mobile Application; (3) in the event of any failure of The Mobile\n" +
                "Application to conform to any applicable warranty, you may notify the\n" +
                "applicable App Distributor, and the App Distributor, in accordance\n" +
                "with its terms and policies, may refund the purchase price, if any,\n" +
                "paid for The Mobile Application, and to the maximum extent permitted\n" +
                "by applicable law, the App Distributor will have no other warranty\n" +
                "obligation whatsoever with respect to The Mobile Application; </FONT></FONT><STRIKE><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">(</FONT></FONT></STRIKE><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">4)\n" +
                "you must comply with applicable third-party terms of agreement when\n" +
                "using The Mobile Application, e.g., if you have a VoIP application,\n" +
                "then you must not be in violation of their wireless data service\n" +
                "agreement when using The Mobile Application; and (5) you acknowledge\n" +
                "and agree that the App Distributors are third-party beneficiaries of\n" +
                "the Terms in this Mobile Application license contained in these\n" +
                "Terms, and that each App Distributor will have the right (and will be\n" +
                "deemed to have accepted the right) to enforce the Terms in this\n" +
                "Mobile Application license contained in these Terms against you as a\n" +
                "third-party beneficiary thereof.&nbsp; &nbsp;&nbsp;</FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\">&nbsp;&nbsp;</P>\n" +
                "<H1 CLASS=\"western\"><A NAME=\"_736fvbpuztsj\"></A>SOCIAL MEDIA</H1>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">As\n" +
                "part of the functionality of the Application, you may link your\n" +
                "account with online accounts you have with third-party service\n" +
                "providers (each such account, a “Third-Party Account”) by either:\n" +
                "(1) providing your Third-Party Account login information through the\n" +
                "Application; or (2) allowing us to access your Third-Party Account,\n" +
                "as is permitted under the applicable Terms that govern your use of\n" +
                "each Third-Party Account. You represent and warrant that you are\n" +
                "entitled to disclose your Third-Party Account login information to us\n" +
                "and/or grant us access to your Third-Party Account, without breach by\n" +
                "you of any of the Terms that govern your use of the applicable\n" +
                "Third-Party Account, and without obligating us to pay any fees or\n" +
                "making us subject to any usage limitations imposed by the third-party\n" +
                "service provider of the Third-Party Account.  By granting us access\n" +
                "to any Third-Party Accounts, you understand that (1) we may access,\n" +
                "make available, and store (if applicable) any content that you have\n" +
                "provided to and stored in your Third-Party Account (the “Social\n" +
                "Network Content”) so that it is available on and through the Site\n" +
                "via your account, including without limitation any friend lists and\n" +
                "(2) we may submit to and receive from your Third-Party Account\n" +
                "additional information to the extent you are notified when you link\n" +
                "your account with the Third-Party Account.  Depending on the\n" +
                "Third-Party Accounts you choose and subject to the privacy settings\n" +
                "that you have set in such Third-Party Accounts, personally\n" +
                "identifiable information that you post to your Third-Party Accounts\n" +
                "may be available on and through your account on the Application.\n" +
                "Please note that if a Third-Party Account or associated service\n" +
                "becomes unavailable or our access to such Third-Party Account is\n" +
                "terminated by the third-party service provider, then Social Network\n" +
                "Content may no longer be available on and through the Application.\n" +
                "You will have the ability to disable the connection between your\n" +
                "account on the Application and your Third-Party Accounts at any time.\n" +
                "PLEASE NOTE THAT YOUR RELATIONSHIP WITH THE THIRD-PARTY SERVICE\n" +
                "PROVIDERS ASSOCIATED WITH YOUR THIRD-PARTY ACCOUNTS IS GOVERNED\n" +
                "SOLELY BY YOUR AGREEMENT(S) WITH SUCH THIRD-PARTY SERVICE PROVIDERS. \n" +
                "We make no effort to review any Social Network Content for any\n" +
                "purpose, including but not limited to, for accuracy, legality, or\n" +
                "non-infringement, and we are not responsible for any Social Network\n" +
                "Content. You acknowledge and agree that we may access your email\n" +
                "address book associated with a Third-Party Account and your contacts\n" +
                "list stored on your mobile device or tablet computer solely for\n" +
                "purposes of identifying and informing you of those contacts who have\n" +
                "also registered to use the Application. \u0004You can deactivate the\n" +
                "connection between the Application and your Third-Party Account by\n" +
                "contacting us using the contact information below or through your\n" +
                "account settings (if applicable)\u0005<!-- This is for Bryan and team -->.\n" +
                "We will attempt to delete any information stored on our servers that\n" +
                "was obtained through such Third-Party Account, except the username\n" +
                "and profile picture that become associated with your account.</FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<H1 CLASS=\"western\"><A NAME=\"_74knogvdugpc\"></A>SUBMISSIONS</H1>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">You\n" +
                "acknowledge and agree that any questions, comments, suggestions,\n" +
                "ideas, feedback, or other information regarding the Application\n" +
                "(&quot;Submissions&quot;) provided by you to us are non-confidential\n" +
                "and shall become our sole property.  We shall own exclusive rights,\n" +
                "including all intellectual property rights, and shall be entitled to\n" +
                "the unrestricted use and dissemination of these Submissions for any\n" +
                "lawful purpose, commercial or otherwise, without acknowledgment or\n" +
                "compensation to you.  You hereby waive all moral rights to any such\n" +
                "Submissions, and you hereby warrant that any such Submissions are\n" +
                "original with you or that you have the right to submit such\n" +
                "Submissions. You agree there shall be no recourse against us for any\n" +
                "alleged or actual infringement or misappropriation of any proprietary\n" +
                "right in your Submissions. </FONT></FONT>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<H1 CLASS=\"western\"><A NAME=\"_ryk9288t8bvy\"></A>THIRD-PARTY WEBSITES\n" +
                "AND CONTENT</H1>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">The\n" +
                "Application may contain (or you may be sent via the Application)\n" +
                "links to other websites (&quot;Third-Party Websites&quot;) as well as\n" +
                "articles, photographs, text, graphics, pictures, designs, music,\n" +
                "sound, video, information, applications, software, and other content\n" +
                "or items belonging to or originating from third parties (&quot;Third-Party\n" +
                "Content&quot;). Such Third-Party Websites and Third-Party Content are\n" +
                "not investigated, monitored, or checked for accuracy,\n" +
                "appropriateness, or completeness by us, and we are not responsible\n" +
                "for any Third-Party Websites accessed through the Application or any\n" +
                "Third-Party Content posted on, available through, or installed from\n" +
                "the Application, including the content, accuracy, offensiveness,\n" +
                "opinions, reliability, privacy practices, or other policies of or\n" +
                "contained in the Third-Party Websites or the Third-Party Content. \n" +
                "Inclusion of, linking to, or permitting the use or installation of\n" +
                "any Third-Party Websites or any Third-Party Content does not imply\n" +
                "approval or endorsement thereof by us.  If you decide to leave the\n" +
                "Application and access the Third-Party Websites or to use or install\n" +
                "any Third-Party Content, you do so at your own risk, and you should\n" +
                "be aware these Terms no longer govern. You should review the\n" +
                "applicable terms and policies, including privacy and data gathering\n" +
                "practices, of any website to which you navigate from the Application\n" +
                "or relating to any applications you use or install from the\n" +
                "Application. Any purchases you make through Third-Party Websites will\n" +
                "be through other websites and from other companies, and we take no\n" +
                "responsibility whatsoever in relation to such purchases which are\n" +
                "exclusively between you and the applicable third party.  You agree\n" +
                "and acknowledge that we do not endorse the products or services\n" +
                "offered on Third-Party Websites and you shall hold us harmless from\n" +
                "any harm caused by your purchase of such products or services.\n" +
                "Additionally, you shall hold us harmless from any losses sustained by\n" +
                "you or harm caused to you relating to or resulting in any way from\n" +
                "any Third-Party Content or any contact with Third-Party Websites. </FONT></FONT>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><A NAME=\"_x50fpkv2wgef\"></A>\n" +
                "<BR>\n" +
                "</P>\n" +
                "<H1 CLASS=\"western\"><A NAME=\"_z3ila0tbxob2\"></A>APPLICATION\n" +
                "MANAGEMENT</H1>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">We\n" +
                "reserve the right, but not the obligation, to:&nbsp;(1) monitor the\n" +
                "Application for violations of these Terms; (2) take appropriate legal\n" +
                "action against anyone who, in our sole discretion, violates the law\n" +
                "or these Terms, including without limitation, reporting such user to\n" +
                "law enforcement authorities; (3) in our sole discretion and without\n" +
                "limitation, refuse, restrict access to, limit the availability of, or\n" +
                "disable (to the extent technologically feasible) any of your\n" +
                "Contributions or any portion thereof; (4) in&nbsp;our sole discretion\n" +
                "and without limitation, notice, or liability, to remove from the\n" +
                "Application or otherwise disable all files and content that are\n" +
                "excessive in size or are in any way burdensome to our systems; and\n" +
                "(5) otherwise manage the Application in a manner designed to protect\n" +
                "our rights and property and to facilitate the proper functioning of\n" +
                "the Site.</FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<H1 CLASS=\"western\"><A NAME=\"_jod8wtwc1vvv\"></A>PRIVACY POLICY</H1>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">We\n" +
                "care about data privacy and security. Please review our Privacy\n" +
                "Policy \u0004</FONT></FONT><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\"><B>[CLICK\n" +
                "HERE]</B></FONT></FONT><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">.\n" +
                "\u0005<!-- Now working on compiling our privacy policy. Will update you once complete -->By\n" +
                "using the Application, you agree to be bound by our Privacy Policy,\n" +
                "which is incorporated into these Terms.  Please be advised the\n" +
                "Application is hosted in The Republic of Singapore.  If you access\n" +
                "the Application from the European Union, North America, ASEAN or any\n" +
                "other region of the world with laws or other requirements governing\n" +
                "personal data collection, use, or disclosure that differ from\n" +
                "applicable laws in The Republic of Singapore, then through your\n" +
                "continued use of the Application or Services, you are transferring\n" +
                "your data to The Republic of Singapore, and you expressly consent to\n" +
                "have your data transferred to and processed in The Republic of\n" +
                "Singapore.</FONT></FONT><FONT FACE=\"Verdana, serif\"><FONT SIZE=2> </FONT></FONT>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\">&nbsp;&nbsp;</P>\n" +
                "<H1 CLASS=\"western\"><A NAME=\"_th8ukiggl6po\"></A>SINGAPORE DIGITAL\n" +
                "COPYRIGHT ACT 2004 NOTICE AND POLICY</H1>\n" +
                "<H2 CLASS=\"western\"><A NAME=\"_pyfabmazva0w\"></A>Notifications</H2>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">We\n" +
                "respect the intellectual property rights of others.  If you believe\n" +
                "that any material available on or through the Application infringes\n" +
                "upon any copyright you own or control, please immediately notify our\n" +
                "Designated Copyright Agent using the contact information provided\n" +
                "below (a “Notification”).  A copy of your Notification will be\n" +
                "sent to the person who posted or stored the material addressed in the\n" +
                "Notification.  Please be advised that pursuant to Singapore law you\n" +
                "may be held liable for damages if you make material\n" +
                "misrepresentations in a Notification. Thus, if you are not sure that\n" +
                "material located on or linked to by the Application infringes your\n" +
                "copyright, you should consider first contacting an attorney.</FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">All\n" +
                "Notifications should meet the requirements of Singapore Copyright Act\n" +
                "2004 and include the following information: (1) A physical or\n" +
                "electronic signature of a person authorized to act on behalf of the\n" +
                "owner of an exclusive right that is allegedly infringed; (2)\n" +
                "identification of the copyrighted work claimed to have been\n" +
                "infringed, or, if multiple copyrighted works on the Application are\n" +
                "covered by the Notification, a representative list of such works on\n" +
                "the Application; (3) identification of the material that is claimed\n" +
                "to be infringing or to be the subject of infringing activity and that\n" +
                "is to be removed or access to which is to be disabled, and\n" +
                "information reasonably sufficient to permit us to locate the\n" +
                "material; (4) information reasonably sufficient to permit us to\n" +
                "contact the complaining party, such as an address, telephone number,\n" +
                "and, if available, an email address at which the complaining party\n" +
                "may be contacted; (5) a statement that the complaining party has a\n" +
                "good faith belief that use of the material in the manner complained\n" +
                "of is not authorized by the copyright owner, its agent, or the law;\n" +
                "and (6)  a statement that the information in the notification is\n" +
                "accurate, and under penalty of perjury, that the complaining party is\n" +
                "authorized to act on behalf of the owner of an exclusive right that\n" +
                "is allegedly infringed upon.</FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<H2 CLASS=\"western\" STYLE=\"text-indent: -0.3in\"><A NAME=\"_6hwkkoht7l6p\"></A>\n" +
                "<I>\t</I>Counter Notification</H2>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">If\n" +
                "you believe your own copyrighted material has been removed from the\n" +
                "Application as a result of a mistake or misidentification, you may\n" +
                "submit a written counter notification to [us/our Designated Copyright\n" +
                "Agent] using the contact information provided below (a “Counter\n" +
                "Notification”). To be an effective Counter Notification under the\n" +
                "Singapore Digital Copyright Act 2004 your Counter Notification must\n" +
                "include substantially the following: (1) identification of the\n" +
                "material that has been removed or disabled and the location at which\n" +
                "the material appeared before it was removed or disabled; (2) a\n" +
                "statement that you will accept service of process from the party that\n" +
                "filed the Notification or the party's agent; (3) your name, address,\n" +
                "and telephone number; (4) a statement under penalty of perjury that\n" +
                "you have a good faith belief that the material in question was\n" +
                "removed or disabled as a result of a mistake or misidentification of\n" +
                "the material to be removed or disabled; and (5) your physical or\n" +
                "electronic signature.</FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"text-indent: -0.3in; margin-bottom: 0in; line-height: 115%\">\n" +
                "<BR>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">If\n" +
                "you send us a valid, written Counter Notification meeting the\n" +
                "requirements described above, we will restore your removed or\n" +
                "disabled material, unless we first receive notice from the party\n" +
                "filing the Notification informing us that such party has filed a\n" +
                "court action to restrain you from engaging in infringing activity\n" +
                "related to the material in question. Please note that if you\n" +
                "materially misrepresent that the disabled or removed content was\n" +
                "removed by mistake or misidentification, you may be liable for\n" +
                "damages, including costs and attorney's fees. Filing a false Counter\n" +
                "Notification constitutes perjury.</FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">Designated\n" +
                "Copyright Agent</FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">[Name]</FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">Attn:\n" +
                "Copyright Agent</FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">[Address]</FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">[City,\n" +
                "State, Postal Code / Zip]</FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">[email]</FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<H1 CLASS=\"western\"><A NAME=\"_97k6yqrr20ny\"></A><A NAME=\"_etmpra24b75j\"></A>\n" +
                "TERMS AND TERMINATION</H1>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">These\n" +
                "Terms shall remain in full force and effect while you use the\n" +
                "Application. WITHOUT LIMITING ANY OTHER PROVISION OF THESE TERMS OF\n" +
                "USE, WE RESERVE THE RIGHT TO, IN OUR SOLE DISCRETION AND WITHOUT\n" +
                "NOTICE OR LIABILITY, DENY ACCESS TO AND USE OF THE APPLICATION, TO\n" +
                "ANY PERSON FOR ANY REASON OR FOR NO REASON, INCLUDING WITHOUT\n" +
                "LIMITATION FOR BREACH OF ANY REPRESENTATION, WARRANTY, OR COVENANT\n" +
                "CONTAINED IN THESE TERMS OF USE OR OF ANY APPLICABLE LAW OR\n" +
                "REGULATION. WE MAY TERMINATE YOUR USE OR PARTICIPATION IN THE\n" +
                "APPLICATION OR DELETE [YOUR ACCOUNT AND] ANY CONTENT OR INFORMATION\n" +
                "THAT YOU POSTED AT ANY TIME, WITHOUT WARNING, IN OUR SOLE DISCRETION.\n" +
                "&nbsp;</FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">If\n" +
                "we terminate or suspend your account for any reason, you are\n" +
                "prohibited from registering and creating a new account under your\n" +
                "name, a fake or borrowed name, or the name of any third party, even\n" +
                "if you may be acting on behalf of the third party. In addition to\n" +
                "terminating or suspending your account, we reserve the right to take\n" +
                "appropriate legal action, including without limitation pursuing\n" +
                "civil, criminal, and injunctive redress.</FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<H1 CLASS=\"western\"><A NAME=\"_iu8vbv7ttwtj\"></A>MODIFICATIONS AND\n" +
                "INTERRUPTIONS \n" +
                "</H1>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">We\n" +
                "reserve the right to change, modify, or remove the contents of the\n" +
                "Application at any time or for any reason at our sole discretion\n" +
                "without notice. However, we have no obligation to update any\n" +
                "information on our Application.  We also reserve the right to modify\n" +
                "or discontinue all or part of the Application without notice at any\n" +
                "time.  We will not be liable to you or any third party for any\n" +
                "modification, price change, suspension, or discontinuance of the\n" +
                "Application.  </FONT></FONT>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">We\n" +
                "cannot guarantee the Application will be available at all times.  We\n" +
                "may experience hardware, software, or other problems or need to\n" +
                "perform maintenance related to the Application, resulting in\n" +
                "interruptions, delays and/or errors.  We reserve the right to change,\n" +
                "revise, update, suspend, discontinue, or otherwise modify the\n" +
                "Application at any time or for any reason without notice to you.  You\n" +
                "agree that we have no liability whatsoever for any loss, damage, or\n" +
                "inconvenience caused by your inability to access or use the\n" +
                "Application during any downtime or discontinuance of the Application.\n" +
                "Nothing in these Terms will be construed to obligate us to maintain\n" +
                "and support the Application or to supply any corrections, updates, or\n" +
                "releases in connection therewith.</FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<H1 CLASS=\"western\" STYLE=\"margin-bottom: 0.03in\"><A NAME=\"_2c83b7zh2yuf\"></A>\n" +
                "GOVERNING LAW \n" +
                "</H1>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">These\n" +
                "Terms and your use of the Application are governed by and construed\n" +
                "in accordance with the laws of The Republic of Singapore</FONT></FONT><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\"><B>\n" +
                "</B></FONT></FONT><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">applicable\n" +
                "to agreements made and to be entirely performed within The Republic\n" +
                "of Singapore, without regard to its conflict of law principles.  </FONT></FONT>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<H1 CLASS=\"western\"><A NAME=\"_uohuk51jb5b4\"></A><A NAME=\"_2vh49k1y3d8n\"></A>\n" +
                "CORRECTIONS</H1>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">There\n" +
                "may be information on the Application that contains typographical\n" +
                "errors, inaccuracies, or omissions that may relate to the Site,\n" +
                "including descriptions, pricing, availability, and various other\n" +
                "information.  We reserve the right to correct any errors,\n" +
                "inaccuracies, or omissions and to change or update the information on\n" +
                "the Application at any time, without prior notice.</FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<H1 CLASS=\"western\"><A NAME=\"_6i7jyluc8ic7\"></A>DISCLAIMER</H1>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">THE\n" +
                "APPLICATION IS PROVIDED ON AN AS-IS AND AS-AVAILABLE BASIS.  YOU\n" +
                "AGREE THAT YOUR USE OF THE APPLICATION SERVICES WILL BE AT YOUR SOLE\n" +
                "RISK. TO THE FULLEST EXTENT PERMITTED BY LAW, WE DISCLAIM ALL\n" +
                "WARRANTIES, EXPRESS OR IMPLIED, IN CONNECTION WITH THE SITE AND YOUR\n" +
                "USE THEREOF, INCLUDING, WITHOUT LIMITATION, THE IMPLIED WARRANTIES OF\n" +
                "MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, AND\n" +
                "NON-INFRINGEMENT. WE MAKE NO WARRANTIES OR REPRESENTATIONS ABOUT THE\n" +
                "ACCURACY OR COMPLETENESS OF THE APPLICATION’S CONTENT OR THE\n" +
                "CONTENT OF ANY WEBSITES LINKED TO THIS APPLICATION AND WE WILL ASSUME\n" +
                "NO LIABILITY OR RESPONSIBILITY FOR ANY (1) ERRORS, MISTAKES, OR\n" +
                "INACCURACIES OF CONTENT AND MATERIALS, (2) PERSONAL INJURY OR\n" +
                "PROPERTY DAMAGE, OF ANY NATURE WHATSOEVER, RESULTING FROM YOUR ACCESS\n" +
                "TO AND USE OF THE APPLICATION AND ITS SERVICES, (3) ANY UNAUTHORIZED\n" +
                "ACCESS TO OR USE OF OUR SECURE SERVERS AND/OR ANY AND ALL PERSONAL\n" +
                "INFORMATION AND/OR FINANCIAL INFORMATION STORED THEREIN, (4) ANY\n" +
                "INTERRUPTION OR CESSATION OF TRANSMISSION TO OR FROM THE APPLICATION,\n" +
                "(5) ANY BUGS, VIRUSES, TROJAN HORSES, OR THE LIKE WHICH MAY BE\n" +
                "TRANSMITTED TO OR THROUGH THE APPLICATION BY ANY THIRD PARTY, AND/OR\n" +
                "(6) ANY ERRORS OR OMISSIONS IN ANY CONTENT AND MATERIALS OR FOR ANY\n" +
                "LOSS OR DAMAGE OF ANY KIND INCURRED AS A RESULT OF THE USE OF ANY\n" +
                "CONTENT POSTED, TRANSMITTED, OR OTHERWISE MADE AVAILABLE VIA THE\n" +
                "APPLICATION. WE DO NOT WARRANT, ENDORSE, GUARANTEE, OR ASSUME\n" +
                "RESPONSIBILITY FOR ANY PRODUCT OR SERVICE ADVERTISED OR OFFERED BY A\n" +
                "THIRD PARTY THROUGH THE APPLICATION, ANY HYPERLINKED WEBSITE, OR ANY\n" +
                "WEBSITE OR MOBILE APPLICATION FEATURED IN ANY BANNER OR OTHER\n" +
                "ADVERTISING, AND WE WILL NOT BE A PARTY TO OR IN ANY WAY BE\n" +
                "RESPONSIBLE FOR MONITORING ANY TRANSACTION BETWEEN YOU AND ANY\n" +
                "THIRD-PARTY PROVIDERS OF PRODUCTS OR SERVICES.&nbsp; AS WITH THE\n" +
                "PURCHASE OF A PRODUCT OR SERVICE THROUGH ANY MEDIUM OR IN ANY\n" +
                "ENVIRONMENT, YOU SHOULD USE YOUR BEST JUDGMENT AND EXERCISE CAUTION\n" +
                "WHERE APPROPRIATE.</FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\">&nbsp;</P>\n" +
                "<H1 CLASS=\"western\"><A NAME=\"_fqb34cu3azh2\"></A>LIMITATIONS OF\n" +
                "LIABILITY</H1>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">IN\n" +
                "NO EVENT WILL WE OR OUR DIRECTORS, EMPLOYEES, OR AGENTS BE LIABLE TO\n" +
                "YOU OR ANY THIRD PARTY FOR ANY DIRECT, INDIRECT, CONSEQUENTIAL,\n" +
                "EXEMPLARY, INCIDENTAL, SPECIAL, OR PUNITIVE DAMAGES, INCLUDING LOST\n" +
                "PROFIT, LOST REVENUE, LOSS OF DATA, LOSS OF PROPERTY OR OTHER DAMAGES\n" +
                "ARISING FROM YOUR USE OF THE APPLICATION, EVEN IF WE HAVE BEEN\n" +
                "ADVISED OF THE POSSIBILITY OF SUCH DAMAGES. </FONT></FONT>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><A NAME=\"_GoBack\"></A>\n" +
                "<BR>\n" +
                "</P>\n" +
                "<H1 CLASS=\"western\"><A NAME=\"_2h1ye2p0gimz\"></A>INDEMNIFICATION</H1>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">You\n" +
                "agree to defend, indemnify, and hold us harmless, including our\n" +
                "subsidiaries, affiliates, and all of our respective officers, agents,\n" +
                "partners, and employees, from and against any loss, damage,\n" +
                "liability, claim, or demand, including reasonable attorneys’ fees\n" +
                "and expenses, made by any third party due to or arising out of: (1)\n" +
                "[your Contributions]; (2) use of the Application;  (3) breach of\n" +
                "these Terms; (4) any breach of your representations and warranties\n" +
                "set forth in these Terms; (5) your violation of the rights of a third\n" +
                "party, including but not limited to intellectual property rights; or\n" +
                "(6) any overt harmful act toward any other user of the Application\n" +
                "with whom you connected via the Application. Notwithstanding the\n" +
                "foregoing, we reserve the right, at your expense, to assume the\n" +
                "exclusive defense and control of any matter for which you are\n" +
                "required to indemnify us, and you agree to cooperate, at your\n" +
                "expense, with our defense of such claims. We will use reasonable\n" +
                "efforts to notify you of any such claim, action, or proceeding which\n" +
                "is subject to this indemnification upon becoming aware of it.&nbsp;</FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\">&nbsp;&nbsp;</P>\n" +
                "<H1 CLASS=\"western\"><A NAME=\"_u44ogvz9wcup\"></A>USER DATA</H1>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">We\n" +
                "will maintain certain data that you transmit to the Application for\n" +
                "the purpose of managing the Application and providing Services.\n" +
                "Although we perform regular routine backups of data, you are solely\n" +
                "responsible for all data that you transmit or that relates to any\n" +
                "activity you have undertaken using the Application.&nbsp;You agree\n" +
                "that we shall have no liability to you for any loss or corruption of\n" +
                "any such data, and you hereby waive any right of action against us\n" +
                "arising from any such loss or corruption of such data.</FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\">&nbsp;&nbsp;</P>\n" +
                "<H1 CLASS=\"western\"><A NAME=\"_xsbb47eal2rb\"></A><A NAME=\"_lk8uc1f0lu5i\"></A>\n" +
                "MISCELLANEOUS</H1>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">These\n" +
                "Terms and any policies or operating rules posted by us on the\n" +
                "Application constitute the entire agreement and understanding between\n" +
                "you and us. Our failure to exercise or enforce any right or provision\n" +
                "of these Terms shall not operate as a waiver of such right or\n" +
                "provision.  These Terms operate to the fullest extent permissible by\n" +
                "law. We may assign any or all of our rights and obligations to others\n" +
                "at any time.  We shall not be responsible or liable for any loss,\n" +
                "damage, delay, or failure to act caused by any cause beyond our\n" +
                "reasonable control.&nbsp; If any provision or part of a provision of\n" +
                "these Terms is determined to be unlawful, void, or unenforceable,\n" +
                "that provision or part of the provision is deemed severable from\n" +
                "these Terms and does not affect the validity and enforceability of\n" +
                "any remaining provisions. There is no joint venture, partnership,\n" +
                "employment or agency relationship created between you and us as a\n" +
                "result of these Terms or use of the Site.  You agree that these Terms\n" +
                "will not be construed against us by virtue of having drafted them.\n" +
                "You hereby waive any and all defenses you may have based on the\n" +
                "electronic form of these Terms and the lack of signing by the parties\n" +
                "hereto to execute these Terms.</FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<TABLE WIDTH=630 CELLPADDING=1 CELLSPACING=0>\n" +
                "\t<COL WIDTH=293>\n" +
                "\t<COL WIDTH=37>\n" +
                "\t<COL WIDTH=293>\n" +
                "\t<TR VALIGN=TOP>\n" +
                "\t\t<TD WIDTH=293 STYLE=\"border: none; padding: 0in\">\n" +
                "\t\t\t<P><A NAME=\"_psz7seomd57z\"></A><BR>\n" +
                "\t\t\t</P>\n" +
                "\t\t</TD>\n" +
                "\t\t<TD WIDTH=37 STYLE=\"border: none; padding: 0in\">\n" +
                "\t\t\t<P><BR>\n" +
                "\t\t\t</P>\n" +
                "\t\t</TD>\n" +
                "\t\t<TD WIDTH=293 STYLE=\"border: none; padding: 0in\">\n" +
                "\t\t\t<P><BR>\n" +
                "\t\t\t</P>\n" +
                "\t\t</TD>\n" +
                "\t</TR>\n" +
                "</TABLE>\n" +
                "<P STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +

                "</BODY>\n" +
                "</HTML>").putExtra("type", "Terms and Conditions")));
        pp.setOnClickListener(v-> startActivity(new Intent(getContext(), ActivitySettingsItems.class).putExtra("tnc","\n" +
                "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">\n" +
                "<HTML>\n" +
                "<HEAD>\n" +
                "\t<META HTTP-EQUIV=\"CONTENT-TYPE\" CONTENT=\"text/html; charset=utf-8\">\n" +
                "\t<TITLE></TITLE>\n" +
                "\t<META NAME=\"GENERATOR\" CONTENT=\"LibreOffice 4.1.6.2 (Linux)\">\n" +
                "\t<META NAME=\"CREATED\" CONTENT=\"20180612;202200000000000\">\n" +
                "\t<META NAME=\"CHANGEDBY\" CONTENT=\"Allan Helen\">\n" +
                "\t<META NAME=\"CHANGED\" CONTENT=\"20180612;203600000000000\">\n" +
                "\t<META NAME=\"AppVersion\" CONTENT=\"16.0000\">\n" +
                "\t<META NAME=\"DocSecurity\" CONTENT=\"0\">\n" +
                "\t<META NAME=\"HyperlinksChanged\" CONTENT=\"false\">\n" +
                "\t<META NAME=\"LinksUpToDate\" CONTENT=\"false\">\n" +
                "\t<META NAME=\"ScaleCrop\" CONTENT=\"false\">\n" +
                "\t<META NAME=\"ShareDoc\" CONTENT=\"false\">\n" +
                "\t<STYLE TYPE=\"text/css\">\n" +

                "\t</STYLE>\n" +
                "</HEAD>\n" +
                "<BODY LANG=\"en-US\" LINK=\"#0563c1\" BGCOLOR=\"#ffffff\" DIR=\"LTR\">\n" +
                "<P ALIGN=CENTER STYLE=\"margin-bottom: 0in; font-variant: small-caps; line-height: 115%\"><A NAME=\"_2cipo4yr3w5d\"></A>\n" +
                "<FONT SIZE=6><FONT FACE=\"Arial, serif\"><FONT SIZE=6 STYLE=\"font-size: 22pt\"><B>GRAVO\n" +
                "MOBILE APP PRIVACY POLICY</B></FONT></FONT></FONT></P>\n" +
                "<P STYLE=\"margin-bottom: 0in\"><BR>\n" +
                "</P>\n" +
                "<H1 CLASS=\"western\" ALIGN=CENTER STYLE=\"margin-top: 0in; line-height: 115%\"><A NAME=\"_dgff3y1ug9ut\"></A>\n" +
                "[MOBILE APP]</H1>\n" +
                "<P STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT COLOR=\"#999999\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">Last\n" +
                "updated [month day, year]</FONT></FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\"><B>Green\n" +
                "Ravolution Pte Ltd (UEN 201208536W)</B></FONT></FONT><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">\n" +
                "(“we” or “us” or “our”) respects the privacy of our users\n" +
                "(“user” or “you”). This Privacy Policy, using the Personal\n" +
                "Data Protection Act PDPA 2012) as reference, explains how we collect,\n" +
                "use and disclose your information when you visit our mobile\n" +
                "application (the “Application”).</FONT></FONT><FONT FACE=\"Times New Roman, serif\"><FONT SIZE=3>\n" +
                "</FONT></FONT><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">\n" +
                "</FONT></FONT><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\"><B>\n" +
                "</B></FONT></FONT><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">Please\n" +
                "read this Privacy Policy carefully.  IF YOU DO NOT AGREE WITH THE\n" +
                "TERMS OF THIS PRIVACY POLICY, PLEASE DO NOT USE THE APPLICATION. </FONT></FONT>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><A NAME=\"_GoBack\"></A>\n" +
                "<BR>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">We\n" +
                "reserve the right to make changes to this Privacy Policy at any time\n" +
                "and for any reason.  We will alert you about any changes by updating\n" +
                "the “Last updated” date of this Privacy Policy.  You are\n" +
                "encouraged to periodically review this Privacy Policy to stay\n" +
                "informed of updates. You will be deemed to have been made aware of,\n" +
                "will be subject to, and will be deemed to have accepted the changes\n" +
                "in any revised Privacy Policy by your continued use of the\n" +
                "Application after the date such revised Privacy Policy is posted.  </FONT></FONT>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">This\n" +
                "Privacy Policy does not apply to the third-party online/mobile store\n" +
                "from which you install the Application or make payments, including\n" +
                "any in-game virtual items, which may also collect and use data about\n" +
                "you.  We are not responsible for any of the data collected by any\n" +
                "such third party. </FONT></FONT>\n" +
                "</P>\n" +
                "<H1 CLASS=\"western\" STYLE=\"line-height: 115%\"><A NAME=\"_eenucvnqa0rq\"></A>\n" +
                "COLLECTION OF YOUR INFORMATION</H1>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">We\n" +
                "may collect information about you in a variety of ways.  The\n" +
                "information we may collect via the Application depends on the content\n" +
                "and services you use, and includes:  </FONT></FONT>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<H2 CLASS=\"western\" STYLE=\"line-height: 115%\"><A NAME=\"_rima4gqmeezw\"></A>\n" +
                "Personal Data \n" +
                "</H2>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">Demographic\n" +
                "and other personally identifiable information (such as your name and\n" +
                "email address) that you voluntarily give to us when choosing to\n" +
                "participate in various activities related to the Application, such as\n" +
                "but not limited to collection scheduling, collection location,\n" +
                "sharing recycling achievements, sending feedback, and responding to\n" +
                "surveys.  If you choose to share data about yourself via your\n" +
                "profile, online chat, or other interactive areas of the Application,\n" +
                "please be advised that all data you disclose in these areas is public\n" +
                "and your data will be accessible to anyone who accesses the\n" +
                "Application.</FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<H2 CLASS=\"western\" STYLE=\"line-height: 115%\"><A NAME=\"_qs8x7isf2hqt\"></A>\n" +
                "Derivative Data  \n" +
                "</H2>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">Information\n" +
                "our servers automatically collect when you access the Application,\n" +
                "such as your native actions that are integral to the Application,\n" +
                "including recycling habits, recycling history, or past recycling\n" +
                "locations, as well as other interactions with the Application and\n" +
                "other users via server log files.  </FONT></FONT>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<H2 CLASS=\"western\" STYLE=\"line-height: 115%\"><A NAME=\"_jmz6d47e30t\"></A>\n" +
                "Financial Data \n" +
                "</H2>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">Financial\n" +
                "information, such as data related to your payment method (e.g. valid\n" +
                "credit card number, card brand, expiration date) that we may collect\n" +
                "when you purchase, order, return, exchange, or request information\n" +
                "about our services from the Application. [We store only very limited,\n" +
                "if any, financial information that we collect. Otherwise, all\n" +
                "financial information is stored by our payment processor, such as but\n" +
                "not limited to [</FONT></FONT><A HREF=\"https://pay.amazon.com/us/help/201491260\"><FONT COLOR=\"#1155cc\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\"><U>Amazon\n" +
                "Payments,</U></FONT></FONT></FONT></A><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">]\n" +
                "[</FONT></FONT><A HREF=\"https://www.authorize.net/company/privacy/\"><FONT COLOR=\"#1155cc\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\"><U>Authornize.Net,</U></FONT></FONT></FONT></A><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">]\n" +
                " [</FONT></FONT><A HREF=\"https://www.braintreepayments.com/legal\"><FONT COLOR=\"#1155cc\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\"><U>Braintree\n" +
                "Payments,</U></FONT></FONT></FONT></A><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">]\n" +
                "[</FONT></FONT><A HREF=\"\\h\"><FONT COLOR=\"#1155cc\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\"><U>Chargify,</U></FONT></FONT></FONT></A><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">]\n" +
                "[</FONT></FONT><A HREF=\"\\h\"><FONT COLOR=\"#1155cc\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\"><U>Dwolla,</U></FONT></FONT></FONT></A><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">]\n" +
                "[</FONT></FONT><A HREF=\"https://payments.google.com/payments/apis-secure/get_legal_document?ldo=0&amp;ldt=privacynotice\"><FONT COLOR=\"#1155cc\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\"><U>Google\n" +
                "Checkout,]</U></FONT></FONT></FONT></A><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">\n" +
                "[</FONT></FONT><A HREF=\"\\h\"><FONT COLOR=\"#1155cc\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\"><U>Paypal</U></FONT></FONT></FONT></A><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">,]\n" +
                "[</FONT></FONT><A HREF=\"\\h\"><FONT COLOR=\"#1155cc\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\"><U>SafeCharge,</U></FONT></FONT></FONT></A><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">]\n" +
                "[</FONT></FONT><A HREF=\"https://stripe.com/us/privacy/\"><FONT COLOR=\"#1155cc\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\"><U>Stripe,</U></FONT></FONT></FONT></A><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">]\n" +
                "[</FONT></FONT><A HREF=\"\\h\"><FONT COLOR=\"#1155cc\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\"><U>WePay,</U></FONT></FONT></FONT></A><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">]\n" +
                "</FONT></FONT><A HREF=\"https://www.2checkout.com/policies/privacy-policy/\"><FONT COLOR=\"#1155cc\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\"><U>[2Checkout,</U></FONT></FONT></FONT></A><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">]\n" +
                "[other], and you are encouraged to review their privacy policy and\n" +
                "contact them directly for responses to your questions.</FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<H2 CLASS=\"western\" STYLE=\"line-height: 115%\"><A NAME=\"_z0yqukvj8bh9\"></A>\n" +
                "Facebook Permissions  \n" +
                "</H2>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">The\n" +
                "Application may by default access your </FONT></FONT><A HREF=\"https://www.facebook.com/about/privacy/\"><FONT COLOR=\"#1155cc\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\"><U>Facebook</U></FONT></FONT></FONT></A><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">\n" +
                "basic account information, including your name, email, gender,\n" +
                "birthday, current city, and profile picture URL, as well as other\n" +
                "information that you choose to make public. We may also request\n" +
                "access to other permissions related to your account, such as friends,\n" +
                "check-ins, and likes, and you may choose to grant or deny us access\n" +
                "to each individual permission. For more information regarding\n" +
                "Facebook permissions, refer to the </FONT></FONT><A HREF=\"https://developers.facebook.com/docs/facebook-login/permissions\"><FONT COLOR=\"#1155cc\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\"><U>Facebook\n" +
                "Permissions Reference </U></FONT></FONT></FONT></A><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">page.</FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<H2 CLASS=\"western\" STYLE=\"line-height: 115%\"><A NAME=\"_64xreagpas2f\"></A>\n" +
                "Data from Social Networks  \n" +
                "</H2>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">User\n" +
                "information from social networking sites, such as [Apple’s Game\n" +
                "Center, Facebook, Google+ Instagram, Pinterest, Twitter], including\n" +
                "your name, your social network username, location, gender, birth\n" +
                "date, email address, profile picture, and public data for contacts,\n" +
                "if you connect your account to such social networks. This information\n" +
                "may also include the contact information of anyone you invite to use\n" +
                "and/or join the Application.</FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<H2 CLASS=\"western\" STYLE=\"line-height: 115%\"><A NAME=\"_1wgqc2u1087n\"></A>\n" +
                "Geo-Location Information \n" +
                "</H2>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><A NAME=\"_Hlk516163394\"></A>\n" +
                "<FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\"><SPAN STYLE=\"background: #ffffff\">We\n" +
                "will request access or permission to and track location-based\n" +
                "information from your mobile device, either continuously or while you\n" +
                "are using the Application, to provide location-based services. This\n" +
                "is necessary because our services to you require us to be present at\n" +
                "your desired location. If you wish to change our access or\n" +
                "permissions, you may do so in your device’s settings, however you\n" +
                "may not be able to use the Application to its optimum capacity.</SPAN></FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<H2 CLASS=\"western\" STYLE=\"line-height: 115%\"><A NAME=\"_ohf32362ce60\"></A>\n" +
                "Mobile Device Access \n" +
                "</H2>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">We\n" +
                "may request access or permission to certain features from your mobile\n" +
                "device, including your mobile device’s [bluetooth, calendar,\n" +
                "camera, microphone, reminders, sensors, SMS messages, social media\n" +
                "accounts, storage,] and other features. </FONT></FONT><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\"><SPAN STYLE=\"background: #ffffff\">If\n" +
                "you wish to change our access or permissions, you may do so in your\n" +
                "device’s settings, however you may not be able to use the\n" +
                "Application to its optimum capacity.</SPAN></FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><SPAN STYLE=\"background: #ffffff\">\n" +
                "</SPAN><BR>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<H2 CLASS=\"western\" STYLE=\"line-height: 115%\"><A NAME=\"_4iy4n37v9m63\"></A>\n" +
                "Mobile Device Data \n" +
                "</H2>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">Device\n" +
                "information such as your mobile device ID number, model, and\n" +
                "manufacturer, version of your operating system, phone number,\n" +
                "country, location, and any other data you choose to provide.</FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<H2 CLASS=\"western\" STYLE=\"line-height: 115%\"><A NAME=\"_z2mmqno9ju37\"></A>\n" +
                "Push Notifications \n" +
                "</H2>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">We\n" +
                "may request to send you push notifications regarding your account or\n" +
                "the Application’s services. If you wish to opt-out from receiving\n" +
                "these types of communications.</FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<H2 CLASS=\"western\" STYLE=\"line-height: 115%\"><A NAME=\"_urn7verkgc6k\"></A>\n" +
                "Third-Party Data \n" +
                "</H2>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">Information\n" +
                "to third parties, such as but not limited to name, address, telephone\n" +
                "number and profile to facilitate in execution of our services to you.</FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<H2 CLASS=\"western\" STYLE=\"line-height: 115%\"><A NAME=\"_b8ijir46a2b\"></A>\n" +
                "Data From Contests, Giveaways, and Surveys \n" +
                "</H2>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">Personal\n" +
                "and other information you may provide when entering the loyalty\n" +
                "program or giveaways and/or responding to surveys.</FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=3><B>Tracking\n" +
                "Technologies</B></FONT></FONT></P>\n" +
                "<H2 CLASS=\"western\" STYLE=\"margin-left: 0.5in; line-height: 115%\">\u0004Cookies\n" +
                "and Web Beacons\u0005<!-- Same question to Jon again…are we using cookies to track users? --></H2>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-left: 0.5in; margin-bottom: 0in; line-height: 115%\">\n" +
                "<FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">We may\n" +
                "use cookies, web beacons, tracking pixels, and other tracking\n" +
                "technologies on the Application to help customize the Application and\n" +
                "improve your experience. When you access the Application, your\n" +
                "personal information is not collected through the use of tracking\n" +
                "technology. Most browsers are set to accept cookies by default. You\n" +
                "can remove or reject cookies, but be aware that such action could\n" +
                "affect the availability and functionality of the Application. You may\n" +
                "not decline web beacons. However, they can be rendered ineffective by\n" +
                "declining all cookies or by modifying your web browser’s settings\n" +
                "to notify you each time a cookie is tendered, permitting you to\n" +
                "accept or decline cookies on an individual basis.</FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<H2 CLASS=\"western\" STYLE=\"margin-left: 0.5in; line-height: 115%\"><A NAME=\"_n6oeggdkjcgr\"></A><A NAME=\"_4fc8mj1uajxp\"></A>\n" +
                "Website Analytics \n" +
                "</H2>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-left: 0.5in; margin-bottom: 0in; line-height: 115%\">\n" +
                "<FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">We may\n" +
                "also partner with selected third-party vendors[, such as [</FONT></FONT><A HREF=\"http://www.adobe.com/privacy/marketing-cloud.html\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\"><U>Adobe\n" +
                "Analytics</U></FONT></FONT></A><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">,]\n" +
                "[</FONT></FONT><A HREF=\"https://support.google.com/analytics/answer/6004245?hl=en\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\"><U>Google\n" +
                "Analytics</U></FONT></FONT></A><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">,]\n" +
                "[</FONT></FONT><A HREF=\"https://heapanalytics.com/privacy\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\"><U>Heap\n" +
                "Analytics</U></FONT></FONT></A><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">,]\n" +
                "[</FONT></FONT><A HREF=\"\\h\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\"><U>Mixpanel,</U></FONT></FONT></A><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">]\n" +
                "[</FONT></FONT><A HREF=\"\\h\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\"><U>Piwik,</U></FONT></FONT></A><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">]\n" +
                "and others], to allow tracking technologies and remarketing services\n" +
                "on the Application through the use of first party cookies and\n" +
                "third-party cookies, to, among other things, analyze and track users’\n" +
                "use of the Application, determine the popularity of certain content,\n" +
                "and better understand online activity. By accessing the Application,\n" +
                "you consent to the collection and use of your information by these\n" +
                "third-party vendors. You are encouraged to review their privacy\n" +
                "policy and contact them directly for responses to your questions. We\n" +
                "do not transfer personal information to these third-party vendors.\n" +
                "However, if you do not want any information to be collected and used\n" +
                "by tracking technologies, you can install and/or update your settings\n" +
                "for one of the \u0004following: </FONT></FONT>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-left: 0.5in; margin-bottom: 0in; line-height: 115%\">\n" +
                "<FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">[</FONT></FONT><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\"><U><A HREF=\"http://www.adobe.com/privacy/opt-out.html\">Adobe\n" +
                "Privacy Choices Page</A><A HREF=\"http://www.adobe.com/privacy/opt-out.html\">]</A></U></FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-left: 0.5in; margin-bottom: 0in; line-height: 115%\">\n" +
                "<FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">[</FONT></FONT><A HREF=\"\\h\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\"><U>Clicktale\n" +
                "Opt-Out Feature</U></FONT></FONT></A><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">]</FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-left: 0.5in; margin-bottom: 0in; line-height: 115%\">\n" +
                "<FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">[</FONT></FONT><A HREF=\"https://www.crazyegg.com/opt-out/\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\"><U>Crazy\n" +
                "Egg Opt-Out Feature</U></FONT></FONT></A><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">]</FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-left: 0.5in; margin-bottom: 0in; line-height: 115%\">\n" +
                "<A HREF=\"http://www.aboutads.info/choices/\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\"><U>Digital\n" +
                "Advertising Alliance Opt-Out Tool</U></FONT></FONT></A></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-left: 0.5in; margin-bottom: 0in; line-height: 115%\">\n" +
                "<FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">[</FONT></FONT><A HREF=\"https://aim.yahoo.com/aim/us/en/optout/\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\"><U>Flurry\n" +
                "Analytics Yahoo Opt-Out Manager</U></FONT></FONT></A><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">]</FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-left: 0.5in; margin-bottom: 0in; line-height: 115%\">\n" +
                "<FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">[</FONT></FONT><A HREF=\"https://tools.google.com/dlpage/gaoptout/\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\"><U>Google\n" +
                "Analytics Opt-Out Plugin</U></FONT></FONT></A><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">]\n" +
                " </FONT></FONT>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-left: 0.5in; margin-bottom: 0in; line-height: 115%\">\n" +
                "<FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">[</FONT></FONT><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\"><U><A HREF=\"https://www.google.com/settings/u/0/ads/authenticated?hl=en\">Google\n" +
                "Ads Settings Page</A>]</U></FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-left: 0.5in; margin-bottom: 0in; line-height: 115%\">\n" +
                "<FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\"><U>[<A HREF=\"\\h\">Inspectlet\n" +
                "Opt-Out Cookie</A>]</U></FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-left: 0.5in; margin-bottom: 0in; line-height: 115%\">\n" +
                "<FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">[</FONT></FONT><A HREF=\"\\h\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\"><U>Kissmetrics\n" +
                "Opt-Out Feature</U></FONT></FONT></A><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">]</FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-left: 0.5in; margin-bottom: 0in; line-height: 115%\">\n" +
                "<FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">[</FONT></FONT><A HREF=\"\\h\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\"><U>Mixpanel\n" +
                "Opt-Out Cookie</U></FONT></FONT></A><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">]</FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-left: 0.5in; margin-bottom: 0in; line-height: 115%\">\n" +
                "<A HREF=\"http://www.networkadvertising.org/choices/\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\"><U><B>Network\n" +
                "Advertising Initiative Opt-Out Tool</B></U></FONT></FONT></A><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\"><B>\n" +
                "\u0005<!-- For Bryan and Jon to advise us --></B></FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-left: 0.5in; margin-bottom: 0in; line-height: 115%\">\n" +
                "<BR>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-left: 0.5in; margin-bottom: 0in; line-height: 115%\">\n" +
                "<FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">You\n" +
                "should be aware that getting a new computer, installing a new\n" +
                "browser, upgrading an existing browser, or erasing or otherwise\n" +
                "altering your browser’s cookies files may also clear certain\n" +
                "opt-out cookies, plug-ins, or settings.</FONT></FONT></P>\n" +
                "<H1 CLASS=\"western\" STYLE=\"line-height: 115%\"><A NAME=\"_v2uc2vjvvxe1\"></A>\n" +
                "USE OF YOUR INFORMATION</H1>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">Having\n" +
                "accurate information about you permits us to provide you with a\n" +
                "smooth, efficient, and customized experience. Specifically, we may\n" +
                "use information collected about you via the Application to: </FONT></FONT>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<OL>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">Create\n" +
                "\tand manage your account</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">Generate\n" +
                "\ta personal profile about you to make future visits to the\n" +
                "\tApplication more personalized.</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">Confirm\n" +
                "\tAge</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">Allow\n" +
                "\tscheduling of recycling pick up</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">Identify\n" +
                "\tand acknowledge top recyclers on the Application.</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">Identify\n" +
                "\tprecise recycling pick up location </FONT></FONT>\n" +
                "\t</P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">Give\n" +
                "\tfinancial compensation to users for recycling</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">Give\n" +
                "\tpoints to users for loyalty program</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">Compile\n" +
                "\tanonymous recycling statistical data and analysis for use internally\n" +
                "\tor with third parties. </FONT></FONT>\n" +
                "\t</P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">Contact\n" +
                "\tyou regarding your account</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">Increase\n" +
                "\tthe efficiency and operation of the Application.</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">Monitor\n" +
                "\tand analyze usage and trends to improve your experience with the\n" +
                "\tApplication.</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">Notify\n" +
                "\tyou of updates to the Application.</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">Offer\n" +
                "\tnew products to recycle, services, mobile applications, and/or\n" +
                "\trecommendations to you.</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">Perform\n" +
                "\tother business activities as needed.</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">Prevent\n" +
                "\tfraudulent transactions, monitor against theft, and protect against\n" +
                "\tcriminal activity.</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">Process\n" +
                "\tpayments and refunds.</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">Request\n" +
                "\tfeedback and contact you about your use of the Application. </FONT></FONT>\n" +
                "\t</P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">Resolve\n" +
                "\tdisputes and troubleshoot problems.</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">Respond\n" +
                "\tto product and customer service requests.</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">Send\n" +
                "\tyou a newsletter</FONT></FONT></P>\n" +
                "</OL>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<H1 CLASS=\"western\" STYLE=\"line-height: 115%\"><BR><BR>\n" +
                "</H1>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><A NAME=\"_56emerdysgpo\"></A>\n" +
                "<BR>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<H1 CLASS=\"western\" STYLE=\"line-height: 115%\"><A NAME=\"_klddry1k9fe6\"></A>\n" +
                "DISCLOSURE OF YOUR INFORMATION</H1>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">We\n" +
                "may share information we have collected about you in certain\n" +
                "situations. Your information may be disclosed as follows: </FONT></FONT>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<H2 CLASS=\"western\" STYLE=\"line-height: 115%\"><A NAME=\"_wmqiyk8z1ifq\"></A>\n" +
                "By Law or to Protect Rights \n" +
                "</H2>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">If\n" +
                "we believe the release of information about you is necessary to\n" +
                "respond to legal process, to investigate or remedy potential\n" +
                "violations of our policies, or to protect the rights, property, and\n" +
                "safety of others, we may share your information as permitted or\n" +
                "required by any applicable law, rule, or regulation.  This includes\n" +
                "exchanging information with other entities for fraud protection and\n" +
                "credit risk reduction.</FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<H2 CLASS=\"western\" STYLE=\"line-height: 115%\"><A NAME=\"_fae8kjk8qmdd\"></A>\n" +
                "Third-Party Service Providers \n" +
                "</H2>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">We\n" +
                "may share your information with third parties that perform services\n" +
                "for us or on our behalf, including payment processing, data analysis,\n" +
                "email delivery, hosting services, customer service, and marketing\n" +
                "assistance.  </FONT></FONT>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<H2 CLASS=\"western\" STYLE=\"line-height: 115%\"><A NAME=\"_m891eoq353db\"></A>\n" +
                "Marketing Communications</H2>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">With\n" +
                "your consent, or with an opportunity for you to withdraw consent, we\n" +
                "may share your information with third parties for CSR marketing\n" +
                "purposes, as permitted by law.</FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<H2 CLASS=\"western\" STYLE=\"line-height: 115%\"><A NAME=\"_1g4s611s98t6\"></A>\n" +
                "Interactions with Other Users \n" +
                "</H2>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">If\n" +
                "you interact with other users of the Application, those users may see\n" +
                "your name, profile photo, and descriptions of your activity,\n" +
                "including sending invitations to other users, chatting with other\n" +
                "users, liking posts, following blogs. </FONT></FONT>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<H2 CLASS=\"western\" STYLE=\"line-height: 115%\"><A NAME=\"_qni5gjh20gty\"></A>\n" +
                "Online Postings</H2>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">When\n" +
                "you post comments, contributions or other content to the\n" +
                "Applications, your posts may be viewed by all users and may be\n" +
                "publicly distributed outside the Application in perpetuity</FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<H2 CLASS=\"western\" STYLE=\"line-height: 115%\"><A NAME=\"_dpeckra1seq2\"></A><A NAME=\"_wvhkv7isu1op\"></A><A NAME=\"_w1o99isgvu3t\"></A>\n" +
                "Affiliates \n" +
                "</H2>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">We\n" +
                "may share your information with our affiliates, in which case we will\n" +
                "require those affiliates to honor this Privacy Policy. Affiliates\n" +
                "include our parent company and any subsidiaries, joint venture\n" +
                "partners or other companies that we control or that are under common\n" +
                "control with us.</FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<H2 CLASS=\"western\" STYLE=\"line-height: 115%\"><A NAME=\"_mlwpc7lo5h0z\"></A>\n" +
                "Business Partners \n" +
                "</H2>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">We\n" +
                "may share your information with our business partners to offer you\n" +
                "certain products, services or promotions. </FONT></FONT>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<H2 CLASS=\"western\" STYLE=\"line-height: 115%\"><A NAME=\"_kloybglpsxbm\"></A>\n" +
                "Offer Wall  \n" +
                "</H2>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><A NAME=\"_mn9wp5k3v2vz\"></A>\n" +
                "<FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">the\n" +
                "Application may display a third-party-hosted “offer wall.”  Such\n" +
                "an offer wall allows third-party advertisers to offer vouchers,\n" +
                "gifts, or other items to users in return for acceptance and\n" +
                "completion of an advertisement offer.  Such an offer wall may appear\n" +
                "in the Application and be displayed to you based on certain data,\n" +
                "such as your geographic area or demographic information.  When you\n" +
                "click on an offer wall, you will leave the Application.  A unique\n" +
                "identifier, such as your user ID, will be shared with the offer wall\n" +
                "provider in order to prevent fraud and properly credit your account. \n" +
                "</FONT></FONT><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\"><B>\n" +
                " </B></FONT></FONT>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<H2 CLASS=\"western\" STYLE=\"line-height: 115%\"><A NAME=\"_1b3lbtrwlnlf\"></A>\n" +
                "Social Media Contacts  \n" +
                "</H2>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">If\n" +
                "you connect to the Application through a social network, your\n" +
                "contacts on the social network will see your name, profile photo, and\n" +
                "descriptions of your activity.</FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<H2 CLASS=\"western\" STYLE=\"line-height: 115%\"><A NAME=\"_j770ddi8r35t\"></A>\n" +
                "Other Third Parties</H2>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">We\n" +
                "may share your information with advertisers and investors for the\n" +
                "purpose of conducting general business analysis. We may also share\n" +
                "your information with such third parties for marketing purposes, as\n" +
                "permitted by law. </FONT></FONT>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<H2 CLASS=\"western\" STYLE=\"line-height: 115%\"><A NAME=\"_gfgj94limquo\"></A>\n" +
                "Sale or Bankruptcy \n" +
                "</H2>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">If\n" +
                "we reorganize or sell all or a portion of our assets, undergo a\n" +
                "merger, or are acquired by another entity, we may transfer your\n" +
                "information to the successor entity.  If we go out of business or\n" +
                "enter bankruptcy, your information would be an asset transferred or\n" +
                "acquired by a third party.  You acknowledge that such transfers may\n" +
                "occur and that the transferee may decline honor commitments we made\n" +
                "in this Privacy Policy. </FONT></FONT>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">We\n" +
                "are not responsible for the actions of third parties with whom you\n" +
                "share personal or sensitive data, and we have no authority to manage\n" +
                "or control third-party solicitations.  If you no longer wish to\n" +
                "receive correspondence, emails or other communications from third\n" +
                "parties, you are responsible for contacting the third party directly.\n" +
                "</FONT></FONT>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><A NAME=\"_p6377q2xgso8\"></A>\n" +
                "<BR>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\"><B>Third\n" +
                "Party Websites</B></FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><A NAME=\"_hs1jfy8yvwt0\"></A>\n" +
                "<FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">The\n" +
                "Application may contain links to third-party websites and\n" +
                "applications of interest, including advertisements and external\n" +
                "services that are not affiliated with us. Once you have used these\n" +
                "links to leave the Application, any information you provide to these\n" +
                "third parties is not covered by this Privacy Policy, and we cannot\n" +
                "guarantee the safety and privacy of your information. Before visiting\n" +
                "and providing any information to any third-party websites, you should\n" +
                "inform yourself of the privacy policies and practices (if any) of the\n" +
                "third party responsible for that website, and should take those steps\n" +
                "necessary to, in your discretion, protect the privacy of your\n" +
                "information. We are not responsible for the content or privacy and\n" +
                "security practices and policies of any third parties, including other\n" +
                "sites, services or applications that may be linked to or from the\n" +
                "Application.</FONT></FONT></P>\n" +
                "<H1 CLASS=\"western\" STYLE=\"line-height: 115%\"><A NAME=\"_odf08ny3ibiq\"></A>\n" +
                "SECURITY OF YOUR INFORMATION</H1>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">We\n" +
                "use administrative, technical, and physical security measures to help\n" +
                "protect your personal information. While we have taken reasonable\n" +
                "steps to secure your personal information, please be aware that, no\n" +
                "security measures are perfect or impenetrable, and no method of data\n" +
                "transmission can be guaranteed against any interception or other type\n" +
                "of misuse.  Any information disclosed online is vulnerable to\n" +
                "interception and misuse by unauthorized parties.</FONT></FONT></P>\n" +
                "<H1 CLASS=\"western\" ALIGN=LEFT STYLE=\"line-height: 115%\"><A NAME=\"_fnsu8nbgwwh0\"></A>\n" +
                "POLICY FOR CHILDREN</H1>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">The\n" +
                "Application is open to all of you who have a desire to play an active\n" +
                "role in activities that contribute to environmental protection.  The\n" +
                "application encourages children to participate in activities that\n" +
                "promote environmental protection, such as but not limited to\n" +
                "recycling material, recycling projects, education involving\n" +
                "eco-friendly systems and processes and related activities, and may\n" +
                "also include payments and/or rewards for the recyclables.  If you are\n" +
                "below 13 years of age, you acknowledge that you have obtained\n" +
                "permission from your parent(s) or legal guardian(s), to use the\n" +
                "Application, and all its features, and any payments resulting\n" +
                "thereof, do not constitute payments for any kind employment linked to\n" +
                "the Company. </FONT></FONT>\n" +
                "</P>\n" +
                "<H1 CLASS=\"western\" STYLE=\"line-height: 115%\"><A NAME=\"_oynxufe0hy32\"></A><A NAME=\"_lchlm3ya7ak\"></A>\n" +
                "OPTIONS REGARDING YOUR INFORMATION</H1>\n" +
                "<H2 CLASS=\"western\" STYLE=\"line-height: 115%\"><A NAME=\"_7uup2m8eoof8\"></A>\n" +
                "Account Information</H2>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">You\n" +
                "may at any time review or change the information in your account or\n" +
                "terminate your account by:</FONT></FONT></P>\n" +
                "<UL>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">Logging\n" +
                "\tinto your account settings and updating your account</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">Contacting\n" +
                "\tus using the contact information provided below</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">[Other]</FONT></FONT></P>\n" +
                "</UL>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">Upon\n" +
                "your request to terminate your account, we will deactivate or delete\n" +
                "your account and information from our active databases. However, some\n" +
                "information may be retained in our files to prevent fraud,\n" +
                "troubleshoot problems, assist with any investigations, enforce our\n" +
                "Terms of Use and/or comply with legal requirements.</FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<H2 CLASS=\"western\" STYLE=\"line-height: 115%\"><A NAME=\"_3mc89ff9i0fu\"></A>\n" +
                "Emails and Communications</H2>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">If\n" +
                "you no longer wish to receive correspondence, emails, or other\n" +
                "communications from us, you may opt-out by:</FONT></FONT></P>\n" +
                "<UL>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">Noting\n" +
                "\tyour preferences at the time you register your account with the\n" +
                "\tApplication</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">Logging\n" +
                "\tinto your account settings and updating your preferences.</FONT></FONT></P>\n" +
                "\t<LI><P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">Contacting\n" +
                "\tus using the contact information provided below</FONT></FONT></P>\n" +
                "</UL>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">If\n" +
                "you no longer wish to receive correspondence, emails, or other\n" +
                "communications from third parties, you are responsible for contacting\n" +
                "the third party directly. </FONT></FONT>\n" +
                "</P>\n" +
                "<H1 CLASS=\"western\" ALIGN=LEFT STYLE=\"line-height: 115%\"><A NAME=\"_v07gykoweind\"></A><A NAME=\"_1na8btj6bp4l\"></A>\n" +
                "CONTACT US</H1>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">If\n" +
                "you have questions or comments about this Privacy Policy, please\n" +
                "contact us at:</FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">Green\n" +
                "Ravolution Pte Ltd</FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">21\n" +
                "Jalan Resak, </FONT></FONT>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">Singapore,\n" +
                "808506</FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">+65\n" +
                "8522 9494</FONT></FONT></P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><FONT FACE=\"Arial, serif\"><FONT SIZE=2 STYLE=\"font-size: 11pt\">recycling@greenravolution.com.sg\n" +
                "                                                                     \n" +
                "                                                      </FONT></FONT>\n" +
                "</P>\n" +
                "<P ALIGN=JUSTIFY STYLE=\"margin-bottom: 0in; line-height: 115%\"><BR>\n" +
                "</P>\n" +
                "</BODY>\n" +
                "</HTML>").putExtra("type","Privacy Policy")));
        return view;
    }
}
