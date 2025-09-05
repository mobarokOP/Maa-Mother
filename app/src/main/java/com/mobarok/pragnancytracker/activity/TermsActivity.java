package com.mobarok.pragnancytracker.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mobarok.pragnancytracker.R;

public class TermsActivity extends AppCompatActivity {


    private CheckBox agreeCheckBox;
    private Button continueButton;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_terms);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);

        // If already agreed, skip this screen
        if (prefs.getBoolean("isAgreed", false)) {
            goToMain();
            finish();
        }

        TextView termsText = findViewById(R.id.terms_text);
        agreeCheckBox = findViewById(R.id.agree_checkbox);
        continueButton = findViewById(R.id.continue_button);





        String htmlHare =
                "<!DOCTYPE html>\n" +
                        "<html lang='en'>\n" +
                        "<body>\n" +
                        "  <h1>Terms, Conditions & Medical Disclaimer</h1>\n" +
                        "  <p class='muted'>Last updated: 05 September 2025</p>\n" +
                        "\n" +
                        "  <div class='lang'>\n" +
                        "    <h2>English</h2>\n" +
                        "    <p>Welcome to our Pregnancy Tracker app. By tapping <b>Agree</b>, you acknowledge that you have read, understood, and accepted these Terms, Conditions, and Medical Disclaimer. If you do not agree, please do not use the app.</p>\n" +
                        "\n" +
                        "    <h2>1) Purpose of the App</h2>\n" +
                        "    <p>This app provides general information for pregnancy tracking, including lists of foods, reminders, and educational content. It is intended for informational purposes only.</p>\n" +
                        "\n" +
                        "    <h2>2) No Medical Advice</h2>\n" +
                        "    <ul>\n" +
                        "      <li>The app does <b>not</b> provide medical advice, diagnosis, or treatment.</li>\n" +
                        "      <li>Content is not a substitute for professional medical advice from qualified healthcare providers.</li>\n" +
                        "      <li>You should always consult your doctor or midwife before following any guidance, diet, exercise, supplements, or lifestyle changes suggested by the app.</li>\n" +
                        "    </ul>\n" +
                        "\n" +
                        "    <h2>3) Emergency & Urgent Care</h2>\n" +
                        "    <p>If you experience warning signs (e.g., severe pain, heavy bleeding, decreased fetal movement, high fever, signs of allergy, shortness of breath), seek <b>immediate</b> medical attention or contact local emergency services.</p>\n" +
                        "\n" +
                        "    <h2>4) Food Lists, Allergies & Interactions</h2>\n" +
                        "    <ul>\n" +
                        "      <li>Food lists are for general education and may not suit your personal medical conditions, allergies, or cultural preferences.</li>\n" +
                        "      <li>Verify allergen information and ingredient labels yourself; the app cannot guarantee accuracy or suitability for everyone.</li>\n" +
                        "      <li>Discuss possible interactions with medications, supplements, or pre-existing conditions with your doctor.</li>\n" +
                        "    </ul>\n" +
                        "\n" +
                        "    <h2>5) User Responsibilities</h2>\n" +
                        "    <ul>\n" +
                        "      <li>Use the app responsibly and verify all critical decisions with a healthcare professional.</li>\n" +
                        "      <li>Provide accurate information if the app requests inputs (e.g., dates, symptoms) for better guidance.</li>\n" +
                        "      <li>Keep your device secure and protect your account/data.</li>\n" +
                        "    </ul>\n" +
                        "\n" +
                        "    <h2>6) Eligibility</h2>\n" +
                        "    <p>You must be of legal age to consent to these terms in your jurisdiction. If you are under that age, use the app only under the guidance of a parent/guardian and your healthcare provider.</p>\n" +
                        "\n" +
                        "    <h2>7) Content Sources & Updates</h2>\n" +
                        "    <p>We strive for accuracy but do not warrant that content is complete, up to date, or error-free. Medical knowledge evolves; content may change without notice.</p>\n" +
                        "\n" +
                        "    <h2>8) Third-Party Links & Services</h2>\n" +
                        "    <p>The app may reference third-party content or services. We are not responsible for such content, availability, or practices.</p>\n" +
                        "\n" +
                        "    <h2>9) Limitation of Liability</h2>\n" +
                        "    <p>To the maximum extent permitted by law, we are not liable for any direct, indirect, incidental, special, consequential, or exemplary damages arising from your use of the app, reliance on its content, or inability to use it. You assume all risks associated with using the app.</p>\n" +
                        "\n" +
                        "    <h2>10) Changes to Terms</h2>\n" +
                        "    <p>We may update these Terms & Disclaimer at any time. Continued use of the app after changes means you accept the updated terms.</p>\n" +
                        "\n" +
                        "    <h2>11) Privacy</h2>\n" +
                        "    <p>We handle your data as described in our Privacy Policy (if available in the app). Review and understand how your data is collected, used, and stored.</p>\n" +
                        "\n" +
                        "    <h2>12) Consent</h2>\n" +
                        "    <p>By tapping <b>Agree</b>, you confirm that you have read this document, understand its contents, and accept all terms and the medical disclaimer.</p>\n" +
                        "\n" +
                        "    <h2>13) Contact</h2>\n" +
                        "    <p>If you have questions or concerns about these Terms & Disclaimer, please contact the app developer or support team from within the app.</p>\n" +
                        "  </div>\n" +
                        "\n" +
                        "  <div class='lang'>\n" +
                        "    <h2>বাংলা (Bangla)</h2>\n" +
                        "    <p>আমাদের প্রেগন্যান্সি ট্র্যাকার অ্যাপে স্বাগতম। <b>Agree</b> বোতামে চাপ দিয়ে আপনি নিশ্চিত করছেন যে আপনি এই শর্তাবলী ও মেডিকেল ডিসক্লেইমার পড়ে বুঝেছেন এবং গ্রহণ করেছেন। যদি আপনি সম্মত না হন, অনুগ্রহ করে অ্যাপ ব্যবহার করবেন না।</p>\n" +
                        "\n" +
                        "    <h2>১) অ্যাপের উদ্দেশ্য</h2>\n" +
                        "    <p>এই অ্যাপটি গর্ভাবস্থার ট্র্যাকিং, শিক্ষামূলক কনটেন্ট, খাবারের তালিকা ও রিমাইন্ডার সহ সাধারণ তথ্য প্রদান করে। এটি কেবল তথ্যগত ব্যবহারের জন্য।</p>\n" +
                        "\n" +
                        "    <h2>২) মেডিকেল অ্যাডভাইস নয়</h2>\n" +
                        "    <ul>\n" +
                        "      <li>এই অ্যাপ কোনোভাবেই চিকিৎসা পরামর্শ, রোগ নির্ণয় বা চিকিৎসা প্রদান করে না।</li>\n" +
                        "      <li>এখানকার কনটেন্ট কখনোই যোগ্য চিকিৎসকের পরামর্শের বিকল্প নয়।</li>\n" +
                        "      <li>অ্যাপে প্রদত্ত কোনো নির্দেশনা, ডায়েট, ব্যায়াম, সাপ্লিমেন্ট বা লাইফস্টাইল পরিবর্তন অনুসরণের আগে অবশ্যই আপনার চিকিৎসক/মিডওয়াইফের সঙ্গে পরামর্শ করুন।</li>\n" +
                        "    </ul>\n" +
                        "\n" +
                        "    <h2>৩) জরুরি অবস্থা</h2>\n" +
                        "    <p>তীব্র ব্যথা, বেশি রক্তক্ষরণ, ভ্রূণের নড়াচড়া কমে যাওয়া, উচ্চ জ্বর, অ্যালার্জির লক্ষণ, শ্বাসকষ্ট ইত্যাদি সতর্কতামূলক লক্ষণ দেখা দিলে অবিলম্বে নিকটস্থ হাসপাতালে যোগাযোগ করুন বা জরুরি সেবায় কল করুন।</p>\n" +
                        "\n" +
                        "    <h2>৪) খাবারের তালিকা, অ্যালার্জি ও ইন্টারঅ্যাকশন</h2>\n" +
                        "    <ul>\n" +
                        "      <li>খাবারের তালিকা সাধারণ জ্ঞানের জন্য; সবার ব্যক্তিগত অবস্থা, রোগ, অ্যালার্জি বা সাংস্কৃতিক পছন্দের সাথে মেলে নাও যেতে পারে।</li>\n" +
                        "      <li>উপকরণ ও অ্যালার্জি সম্পর্কিত তথ্য নিজে যাচাই করুন; অ্যাপ সবার জন্য ১০০% নির্ভুলতা বা উপযোগিতা নিশ্চিত করতে পারে না।</li>\n" +
                        "      <li>আপনার চলমান ওষুধ/সাপ্লিমেন্ট বা পূর্ববর্তী রোগের সাথে সম্ভাব্য ইন্টারঅ্যাকশন সম্পর্কে চিকিৎসকের সাথে আলোচনা করুন।</li>\n" +
                        "    </ul>\n" +
                        "\n" +
                        "    <h2>৫) ব্যবহারকারীর দায়িত্ব</h2>\n" +
                        "    <ul>\n" +
                        "      <li>অ্যাপ দায়িত্বশীলভাবে ব্যবহার করুন এবং গুরুত্বপূর্ণ সিদ্ধান্ত নেওয়ার আগে চিকিৎসকের পরামর্শ নিন।</li>\n" +
                        "      <li>অ্যাপ যদি কোনো তথ্য (যেমন তারিখ, উপসর্গ) চায়, সঠিক তথ্য দিন—তাহলে ফলাফল আরও প্রাসঙ্গিক হবে।</li>\n" +
                        "      <li>নিজের ডিভাইস ও অ্যাকাউন্ট/ডেটা নিরাপদ রাখুন।</li>\n" +
                        "    </ul>\n" +
                        "\n" +
                        "    <h2>৬) যোগ্যতা</h2>\n" +
                        "    <p>আপনার এলাকার আইন অনুযায়ী সম্মতি দেওয়ার উপযুক্ত বয়স হতে হবে। বয়স কম হলে অভিভাবকের তত্ত্বাবধানে এবং চিকিৎসকের পরামর্শ নিয়ে ব্যবহার করুন।</p>\n" +
                        "\n" +
                        "    <h2>৭) কনটেন্টের উৎস ও আপডেট</h2>\n" +
                        "    <p>আমরা যথাসাধ্য সঠিক তথ্য দেওয়ার চেষ্টা করি, তবে সব তথ্য সর্বদা সম্পূর্ণ/হালনাগাদ/ত্রুটিমুক্ত হবে—এটি আমরা নিশ্চিত করতে পারি না। চিকিৎসা বিজ্ঞানের তথ্য সময়ে সময়ে পরিবর্তিত হতে পারে।</p>\n" +
                        "\n" +
                        "    <h2>৮) তৃতীয়-পক্ষ লিংক/সেবা</h2>\n" +
                        "    <p>অ্যাপে তৃতীয়-পক্ষ কনটেন্ট/সেবার উল্লেখ থাকতে পারে। এসব কনটেন্ট, প্রাপ্যতা বা অনুশীলনের জন্য আমরা দায়ী নই।</p>\n" +
                        "\n" +
                        "    <h2>৯) দায়-সীমাবদ্ধতা</h2>\n" +
                        "    <p>আইন যতদূর অনুমতি দেয়, এই অ্যাপ ব্যবহারের ফলে বা কনটেন্টের ওপর নির্ভর করার ফলে সৃষ্ট কোনো প্রত্যক্ষ/পরোক্ষ/বিশেষ/আকস্মিক/অনুষঙ্গিক ক্ষতির জন্য আমরা দায়ী থাকব না। অ্যাপ ব্যবহারের সমস্ত ঝুঁকি ব্যবহারকারীর নিজ দায়িত্ব।</p>\n" +
                        "\n" +
                        "    <h2>১০) শর্তাবলী পরিবর্তন</h2>\n" +
                        "    <p>এই শর্তাবলী/ডিসক্লেইমার যেকোনো সময় পরিবর্তন হতে পারে। পরিবর্তনের পর অ্যাপ ব্যবহার অব্যাহত রাখলে ধরে নেওয়া হবে আপনি নতুন শর্তাবলী মেনে নিয়েছেন।</p>\n" +
                        "\n" +
                        "    <h2>১১) গোপনীয়তা</h2>\n" +
                        "    <p>আপনার ডেটা আমরা অ্যাপের গোপনীয়তা নীতিমালা (যদি অ্যাপে উল্লিখিত থাকে) অনুযায়ী পরিচালনা করি। আপনার ডেটা কীভাবে সংগ্রহ, ব্যবহার ও সংরক্ষণ করা হয়—তা অনুগ্রহ করে পড়ে বুঝে নিন।</p>\n" +
                        "\n" +
                        "    <h2>১২) সম্মতি</h2>\n" +
                        "    <p><b>Agree</b> বোতামে চাপ দিয়ে আপনি নিশ্চিত করছেন যে আপনি এই শর্তাবলী ও ডিসক্লেইমার পড়ে বুঝেছেন এবং গ্রহণ করেছেন।</p>\n" +
                        "\n" +
                        "    <h2>১৩) যোগাযোগ</h2>\n" +
                        "    <p>এই শর্তাবলী/ডিসক্লেইমার সম্পর্কে কোনো প্রশ্ন থাকলে অ্যাপের ডেভেলপার/সাপোর্টের সাথে যোগাযোগ করুন।</p>\n" +
                        "  </div>\n" +
                        "\n" +
                        "</body>\n" +
                        "</html>\n";




        termsText.setText(Html.fromHtml(htmlHare));

        continueButton.setOnClickListener(v -> {
            if (agreeCheckBox.isChecked()) {
                prefs.edit().putBoolean("isAgreed", true).apply();
                goToMain();
            } else {
                agreeCheckBox.setError("Please agree to continue");
            }
        });
    }

    private void goToMain() {
        startActivity(new Intent(TermsActivity.this, IntroActivity.class));
    }
}