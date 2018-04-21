package com.greenravolution.gravo.MainFragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
        Button tnc = view.findViewById(R.id.tnc);
        Button pp = view.findViewById(R.id.pp);
        Button ag = view.findViewById(R.id.ag);
        ag.setOnClickListener(v->startActivity(new Intent(getContext(), ActivityWebView.class)));
        tnc.setOnClickListener(v-> startActivity(new Intent(getContext(), ActivitySettingsItems.class).putExtra("tnc","Introduction\n" +
                "These Website Standard Terms and Conditions written on this webpage shall manage your use of this website. These Terms will be applied fully and affect to your use of this Website. By using this Website, you agreed to accept all terms and conditions written in here. You must not use this Website if you disagree with any of these Website Standard Terms and Conditions.\n" +
                "\n" +
                "Minors or people below 18 years old are not allowed to use this Website.\n" +
                "\n" +
                "Intellectual Property Rights\n" +
                "Other than the content you own, under these Terms, greenravolution and/or its licensors own all the intellectual property rights and materials contained in this Website.\n" +
                "\n" +
                "You are granted limited license only for purposes of viewing the material contained on this Website.\n" +
                "\n" +
                "Restrictions\n" +
                "You are specifically restricted from all of the following\n" +
                "\n" +
                "publishing any Website material in any other media;\n" +
                "selling, sublicensing and/or otherwise commercializing any Website material;\n" +
                "publicly performing and/or showing any Website material;\n" +
                "using this Website in any way that is or may be damaging to this Website;\n" +
                "using this Website in any way that impacts user access to this Website;\n" +
                "using this Website contrary to applicable laws and regulations, or in any way may cause harm to the Website, or to any person or business entity;\n" +
                "engaging in any data mining, data harvesting, data extracting or any other similar activity in relation to this Website;\n" +
                "using this Website to engage in any advertising or marketing.\n" +
                "Certain areas of this Website are restricted from being access by you and greenravolution may further restrict access by you to any areas of this Website, at any time, in absolute discretion. Any user ID and password you may have for this Website are confidential and you must maintain confidentiality as well.\n" +
                "\n" +
                "Your Content\n" +
                "In these Website Standard Terms and Conditions, “Your Content” shall mean any audio, video text, images or other material you choose to display on this Website. By displaying Your Content, you grant greenravolution a non-exclusive, worldwide irrevocable, sub licensable license to use, reproduce, adapt, publish, translate and distribute it in any and all media.\n" +
                "\n" +
                "Your Content must be your own and must not be invading any third-party’s rights. greenravolution reserves the right to remove any of Your Content from this Website at any time without notice.\n" +
                "\n" +
                "No warranties\n" +
                "This Website is provided “as is,” with all faults, and greenravolution express no representations or warranties, of any kind related to this Website or the materials contained on this Website. Also, nothing contained on this Website shall be interpreted as advising you.\n" +
                "\n" +
                "Limitation of liability\n" +
                "In no event shall greenravolution, nor any of its officers, directors and employees, shall be held liable for anything arising out of or in any way connected with your use of this Website whether such liability is under contract.  greenravolution, including its officers, directors and employees shall not be held liable for any indirect, consequential or special liability arising out of or in any way related to your use of this Website.\n" +
                "\n" +
                "Indemnification\n" +
                "You hereby indemnify to the fullest extent greenravolution from and against any and/or all liabilities, costs, demands, causes of action, damages and expenses arising in any way related to your breach of any of the provisions of these Terms.\n" +
                "\n" +
                "Severability\n" +
                "If any provision of these Terms is found to be invalid under any applicable law, such provisions shall be deleted without affecting the remaining provisions herein.\n" +
                "\n" +
                "Variation of Terms\n" +
                "greenravolution is permitted to revise these Terms at any time as it sees fit, and by using this Website you are expected to review these Terms on a regular basis.\n" +
                "\n" +
                "Assignment\n" +
                "The greenravolution is allowed to assign, transfer, and subcontract its rights and/or obligations under these Terms without any notification. However, you are not allowed to assign, transfer, or subcontract any of your rights and/or obligations under these Terms.\n" +
                "\n" +
                "Entire Agreement\n" +
                "These Terms constitute the entire agreement between greenravolution and you in relation to your use of this Website, and supersede all prior agreements and understandings.\n" +
                "\n" +
                "Governing Law & Jurisdiction\n" +
                "These Terms will be governed by and interpreted in accordance with the laws of the State of singapore, and you submit to the non-exclusive jurisdiction of the state and federal courts located in singapore for the resolution of any disputes.\n" +
                "\n" +
                "These terms and conditions have been generated at Terms And Conditions Sample.com.").putExtra("type", "Terms and Conditions")));
        pp.setOnClickListener(v-> startActivity(new Intent(getContext(), ActivitySettingsItems.class).putExtra("tnc","greenravolution.com Privacy Policy\n" +
                "This privacy policy has been compiled to better serve those who are concerned with how their 'Personally Identifiable Information' (PII) is being used online. PII, as described in US privacy law and information security, is information that can be used on its own or with other information to identify, contact, or locate a single person, or to identify an individual in context. Please read our privacy policy carefully to get a clear understanding of how we collect, use, protect or otherwise handle your Personally Identifiable Information in accordance with our website.\n" +
                "\n" +
                "What personal information do we collect from the people that visit our blog, website or app?\n" +
                "\n" +
                "When ordering or registering on our site, as appropriate, you may be asked to enter your name, email address, mailing address, phone number or other details to help you with your experience.\n" +
                "\n" +
                "When do we collect information?\n" +
                "\n" +
                "We collect information from you when you register on our site, subscribe to a newsletter or enter information on our site.\n" +
                "\n" +
                "\n" +
                "How do we use your information?\n" +
                "\n" +
                "We may use the information we collect from you when you register, make a purchase, sign up for our newsletter, respond to a survey or marketing communication, surf the website, or use certain other site features in the following ways:\n" +
                "\n" +
                "      • To improve our website in order to better serve you.\n" +
                "      • To allow us to better service you in responding to your customer service requests.\n" +
                "      • To administer a contest, promotion, survey or other site feature.\n" +
                "      • To quickly process your transactions.\n" +
                "      • To ask for ratings and reviews of services or products\n" +
                "      • To follow up with them after correspondence (live chat, email or phone inquiries)\n" +
                "\n" +
                "How do we protect your information?\n" +
                "\n" +
                "We do not use vulnerability scanning and/or scanning to PCI standards.\n" +
                "We only provide articles and information. We never ask for credit card numbers.\n" +
                "We do not use Malware Scanning.\n" +
                "\n" +
                "Your personal information is contained behind secured networks and is only accessible by a limited number of persons who have special access rights to such systems, and are required to keep the information confidential. In addition, all sensitive/credit information you supply is encrypted via Secure Socket Layer (SSL) technology.\n" +
                "\n" +
                "We implement a variety of security measures when a user places an order enters, submits, or accesses their information to maintain the safety of your personal information.\n" +
                "\n" +
                "All transactions are processed through a gateway provider and are not stored or processed on our servers.\n" +
                "\n" +
                "Do we use 'cookies'?\n" +
                "\n" +
                "We do not use cookies for tracking purposes\n" +
                "\n" +
                "You can choose to have your computer warn you each time a cookie is being sent, or you can choose to turn off all cookies. You do this through your browser settings. Since browser is a little different, look at your browser's Help Menu to learn the correct way to modify your cookies.\n" +
                "\n" +
                "If you turn cookies off .\n" +
                "\n" +
                "Third-party disclosure\n" +
                "\n" +
                "We do not sell, trade, or otherwise transfer to outside parties your Personally Identifiable Information.\n" +
                "\n" +
                "Third-party links\n" +
                "\n" +
                "Occasionally, at our discretion, we may include or offer third-party products or services on our website. These third-party sites have separate and independent privacy policies. We therefore have no responsibility or liability for the content and activities of these linked sites. Nonetheless, we seek to protect the integrity of our site and welcome any feedback about these sites.\n" +
                "\n" +
                "Google\n" +
                "\n" +
                "Google's advertising requirements can be summed up by Google's Advertising Principles. They are put in place to provide a positive experience for users. https://support.google.com/adwordspolicy/answer/1316548?hl=en \n" +
                "\n" +
                "We have not enabled Google AdSense on our site but we may do so in the future.\n" +
                "\n" +
                "California Online Privacy Protection Act\n" +
                "\n" +
                "CalOPPA is the first state law in the nation to require commercial websites and online services to post a privacy policy. The law's reach stretches well beyond California to require any person or company in the United States (and conceivably the world) that operates websites collecting Personally Identifiable Information from California consumers to post a conspicuous privacy policy on its website stating exactly the information being collected and those individuals or companies with whom it is being shared. - See more at: http://consumercal.org/california-online-privacy-protection-act-caloppa/#sthash.0FdRbT51.dpuf\n" +
                "\n" +
                "According to CalOPPA, we agree to the following:\n" +
                "Users can visit our site anonymously.\n" +
                "Once this privacy policy is created, we will add a link to it on our home page or as a minimum, on the first significant page after entering our website.\n" +
                "Our Privacy Policy link includes the word 'Privacy' and can easily be found on the page specified above.\n" +
                "\n" +
                "You will be notified of any Privacy Policy changes:\n" +
                "      • On our Privacy Policy Page\n" +
                "Can change your personal information:\n" +
                "      • By logging in to your account\n" +
                "\n" +
                "How does our site handle Do Not Track signals?\n" +
                "We honor Do Not Track signals and Do Not Track, plant cookies, or use advertising when a Do Not Track (DNT) browser mechanism is in place.\n" +
                "\n" +
                "Does our site allow third-party behavioral tracking?\n" +
                "It's also important to note that we do not allow third-party behavioral tracking\n" +
                "\n" +
                "COPPA (Children Online Privacy Protection Act)\n" +
                "\n" +
                "When it comes to the collection of personal information from children under the age of 13 years old, the Children's Online Privacy Protection Act (COPPA) puts parents in control. The Federal Trade Commission, United States' consumer protection agency, enforces the COPPA Rule, which spells out what operators of websites and online services must do to protect children's privacy and safety online.\n" +
                "\n" +
                "We market to\n" +
                "We do not collect information from children under 13\n" +
                "children under 13.\n" +
                "Do we let third-parties, including ad networks or plug-ins collect PII from children under 13?\n" +
                "No\n" +
                "\n" +
                "In order to remove your child's information please contact the following personnel:\n" +
                "\n" +
                "We adhere to the following COPPA tenants:\n" +
                "      • Parents can review, delete, manage or refuse with whom their child's information is shared through contacting us directly.\n" +
                "or contacting us directly.\n" +
                "\n" +
                "\n" +
                "Fair Information Practices\n" +
                "\n" +
                "The Fair Information Practices Principles form the backbone of privacy law in the United States and the concepts they include have played a significant role in the development of data protection laws around the globe. Understanding the Fair Information Practice Principles and how they should be implemented is critical to comply with the various privacy laws that protect personal information.\n" +
                "\n" +
                "In order to be in line with Fair Information Practices we will take the following responsive action, should a data breach occur:\n" +
                "We will notify you via email\n" +
                "      • Within 1 business day\n" +
                "We will notify the users via in-site notification\n" +
                "      • Within 1 business day\n" +
                "\n" +
                "We also agree to the Individual Redress Principle which requires that individuals have the right to legally pursue enforceable rights against data collectors and processors who fail to adhere to the law. This principle requires not only that individuals have enforceable rights against data users, but also that individuals have recourse to courts or government agencies to investigate and/or prosecute non-compliance by data processors.\n" +
                "\n" +
                "CAN SPAM Act\n" +
                "\n" +
                "The CAN-SPAM Act is a law that sets the rules for commercial email, establishes requirements for commercial messages, gives recipients the right to have emails stopped from being sent to them, and spells out tough penalties for violations.\n" +
                "\n" +
                "We collect your email address in order to:\n" +
                "      • Send information, respond to inquiries, and/or other requests or questions\n" +
                "      • Process orders and to send information and updates pertaining to orders.\n" +
                "      • Send you additional information related to your product and/or service\n" +
                "\n" +
                "To be in accordance with CANSPAM, we agree to the following:\n" +
                "\n" +
                "If at any time you would like to unsubscribe from receiving future emails, you can email us at\n" +
                "and we will promptly remove you from ALL correspondence.\n" +
                "\n" +
                "Contacting Us\n" +
                "\n" +
                "If there are any questions regarding this privacy policy, you may contact us using the information below.\n" +
                "\n" +
                "greenravolution.com\n" +
                "520278\n" +
                "Singapore, singapore 520278\n" +
                "Singapore\n" +
                "signups@greenravolution.com\n" +
                "\n" +
                "Last Edited on 2018-04-20").putExtra("type","Privacy Policy")));
        return view;
    }

}
