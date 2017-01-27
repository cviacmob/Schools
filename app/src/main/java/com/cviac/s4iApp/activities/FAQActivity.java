package com.cviac.s4iApp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;

import com.cviac.s4iApp.R;
import com.cviac.s4iApp.adapters.Faqadapter;
import com.cviac.s4iApp.datamodel.FAQ;
import com.cviac.s4iApp.datamodel.Question;
import com.cviac.s4iApp.datamodel.Topic;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FAQActivity extends AppCompatActivity {
    List<String> ChildList;
    Map<String, List<String>> ParentListItems;
    ExpandableListView expandablelistView;

    private FAQ faq;

    // Assign Parent list items here.
    List<String> ParentList = new ArrayList<String>();

    {
        ParentList.add("MEMBERSHIP");
        ParentList.add("VOLUNTEERING");
        ParentList.add("TEACHING STAFF");
        ParentList.add("S4I ENGAGEMENT");
        ParentList.add("CURRICULUM");
    }

    String[] AndroidName = {"How many members with S4I?"
            + "S4I membership campaign started injune 2008 and we expecting to add by 100 members by end of march 2009."
            + "we are expecting 3000members globally in the next three years."
            + "what is the process to be followed if I as S4I members wish to implement my idea in it?"};
    String[] volunteering = {"default message "};
    String[] teachingstaff = {" default message"};
    String[] s4iengagement = {"default message "};
    String[] curriculum = {" default message"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freq);
        setTitle("FAQ");
        loadFAQ();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ParentListItems = new LinkedHashMap<String, List<String>>();

        for (String HoldItem : ParentList) {
            if (HoldItem.equals("MEMBERSHIP")) {
                loadChild(AndroidName);
            }

            ParentListItems.put(HoldItem, ChildList);
        }

        expandablelistView = (ExpandableListView) findViewById(R.id.expandableListView1);
        // final ExpandableListAdapter expListAdapter = new ListAdapter(this,
        // ParentList, ParentListItems);
        final ExpandableListAdapter expListAdapter = new Faqadapter(this, faq);
        expandablelistView.setAdapter(expListAdapter);

        expandablelistView.setOnChildClickListener(new OnChildClickListener() {

            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition,
                                        long id) {
                // TODO Auto-generated method stub

                // final String selected = (String)
                // expListAdapter.getChild(groupPosition, childPosition);
                // Toast.makeText(getBaseContext(), selected,
                // Toast.LENGTH_LONG).show();

                return true;
            }
        });
    }

    private void loadChild(String[] ParentElementsName) {
        ChildList = new ArrayList<String>();
        for (String model : ParentElementsName)
            ChildList.add(model);

    }

    private void loadFAQ() {
        faq = new FAQ();

        Topic tp = new Topic();
        tp.setName("Membership");

        Question q = new Question();
        q.setQuestion("How many members with S4I?");
        q.setAnswer("S4I membership campaign started in June 2008 and we are expecting to add 100 members by end of March 2009.");
        tp.addQuestion(q);

        q = new Question();
        q.setQuestion("what is the process to be followed if I as S4I members ?");
        q.setAnswer("S4I member groups are formed and the Google group is the forum to share ideas and suggest implementation models.");
        tp.addQuestion(q);

        q = new Question();
        q.setQuestion("How can member funding help S4I ?");
        q.setAnswer("The complete budget and expenses done with member funding is available in the Google Group. ");
        tp.addQuestion(q);

        q = new Question();
        q.setQuestion("Where can we see the Minutes of various meetings held by members?");
        q.setAnswer("The members can view the S4I activities once they get access to the Google group.");
        tp.addQuestion(q);

        q = new Question();
        q.setQuestion("Will membership program continue even if we get adequate support from the corporate?");
        q.setAnswer("The membership fee is intended for supporting the organization structure and the member groups for every district.");
        tp.addQuestion(q);

        faq.addTopic(tp);

        Topic tp2 = new Topic();
        tp2.setName("Volunterring");

        q = new Question();
        q.setQuestion("Do I Need to be in India to be a volunteer?");
        q.setAnswer("No, you can be anywhere in the world, but accessible through e-mail.");
        tp2.addQuestion(q);

        q = new Question();
        q.setQuestion("Do I need to pay any fee or contribute monetarily to the project?");
        q.setAnswer("No, you need not contribute monetarily.");
        tp2.addQuestion(q);

        q = new Question();
        q.setQuestion("If I incur any expenses while performing the work, will I be reimbursed?");
        q.setAnswer("All expenses like travel etc would be reimbursed at actuals. You need to obtain prior permission from the Regional Director for such expenditure.");
        tp2.addQuestion(q);

        faq.addTopic(tp2);

        Topic tp3 = new Topic();
        tp3.setName("Teaching staff");

        q = new Question();
        q.setQuestion("How will be the teaching staff will be recruited?");
        q.setAnswer("S4I will follow the 60:20:20, approach where by 60% of the teachers.");
        tp3.addQuestion(q);

        q = new Question();
        q.setQuestion("What is the method of recruitment?");
        q.setAnswer("S4I will conduct a bridge course for aspiring teachers and selection process.");
        tp3.addQuestion(q);

        q = new Question();
        q.setQuestion("What are the Qualifications for a person to become a teaching staff?");
        q.setAnswer("The teachers will be recruited as per the government norms and regulations.");
        tp3.addQuestion(q);

        q = new Question();
        q.setQuestion("How many teachers per school?");
        q.setAnswer("Each school shall have 140 teaching and Non teaching staff per shift.");
        tp3.addQuestion(q);

        q = new Question();
        q.setQuestion("Say there isn’t enough teachers in the district of specified qualification, how do S4I get enough teachers for that school?");
        q.setAnswer("We do not foresee such a possibility right now. In the long run, S4I will set up dedicated special training centers for training the teachers and will have close working relationship with existing training institutions in that district.");
        tp3.addQuestion(q);

        faq.addTopic(tp3);
        Topic tp4 = new Topic();
        tp4.setName("S4I Engagement");

        q = new Question();
        q.setQuestion(" Will Government fund for any of the school expenses? What is the Government’s part in S4I? Will government monitor S4I administration?");
        q.setAnswer("The funding will depend on the agreements signed by S4I with State or Central government");
        tp4.addQuestion(q);

        q = new Question();
        q.setQuestion("What control will Government have on S4I?");
        q.setAnswer("S4I is a registered Trust and will work autonomously in performing its objects.");
        tp4.addQuestion(q);

        q = new Question();
        q.setQuestion("Have we contacted any corporate to support the pilot project?");
        q.setAnswer("S4I is a registered Trust and will work autonomously in performing its objectsWe are contacting many corporate companies globally through our network. We are getting positive response and certain commitments as well.");
        tp4.addQuestion(q);

        q = new Question();
        q.setQuestion("What do you mean by corporate funding and why will a corporate be interested to support for a initiative like S4I?");
        q.setAnswer("Global corporations who are entering the Indian markets are setting up green field plants across India. By participating in a social project like building a school in a district will enable the corporate company to reach out to the community in which they are doing business");
        tp4.addQuestion(q);

        q = new Question();
        q.setQuestion("How long will be a corporate fund for S4I, say after 5 years if they stop funding then how will be the funds managed for continuing S4I?");
        q.setAnswer("The S4I will continuously de risk the project by having enough resources to meet such eventuality. The funding and risk management will be an ongoing process.");
        tp4.addQuestion(q);

        q = new Question();
        q.setQuestion("Are we working in hand with the local NGO’s?If yes, what would be the stand of S4I, if the local NGO’s would like to do things in their own way?");
        q.setAnswer("S4I will carefully select the local partner for each district, in case of conflict the S4I regional committee will try to resolve the issues, as S4I will have documented process for all such engagements and a conflict resolution process, and this will be refined as we gain experience in dealing.");
        tp4.addQuestion(q);

        q = new Question();
        q.setQuestion("How do you engage the students to support S4I and is there any defined process for the same?");
        q.setAnswer("S4I currently engages many volunteers from Colleges across the globe. Most of the new process developments and support systems are created by these students.");
        tp4.addQuestion(q);

        faq.addTopic(tp4);
        Topic tp5 = new Topic();
        tp5.setName("curriculum");

        q = new Question();
        q.setQuestion("What will be curriculum that will be followed in the S4I School?");
        q.setAnswer("The curriculum shall be decided by the S4I district members based on the local needs.");
        tp5.addQuestion(q);

        q = new Question();
        q.setQuestion("What will be the medium of education?");
        q.setAnswer("The mother tongue shall be the medium of education with supplemental English language training shall be provided.");
        tp5.addQuestion(q);

        faq.addTopic(tp5);
        Topic tp6 = new Topic();
        tp6.setName("Infrastructure");

        q = new Question();
        q.setQuestion("Why build such a big school in rural area?");
        q.setAnswer("Based on our studies we find that maintaining a large school is much easier than maintaining many small schools. By providing large and well managed facility will attract the right teaching talent than small schools");
        tp6.addQuestion(q);

        q = new Question();
        q.setQuestion("What is the method of recruitment?Why spend large amount for one school, instead you can build or support many schools or students?");
        q.setAnswer("The idea is to provide quality education and not compromise on infrastructure or on teaching quality. It is true that we can support many schools with the same money but we would like to set a benchmark and standard with respect to the infrastructure, quality of teachers and the education provided.");
        tp6.addQuestion(q);

        q = new Question();
        q.setQuestion("How are you planning to build such a large school in 90 Days?");
        q.setAnswer("S4I is working with various architects and builders to create a unique building construction plan, using pre fabricated modules which can be assembled in short span of time");
        tp6.addQuestion(q);

        faq.addTopic(tp6);
        Topic tp7 = new Topic();
        tp7.setName("Promotions");

        q = new Question();
        q.setQuestion("Why S4I is promoting Cycling sport in India?");
        q.setAnswer("S4I believes that by promoting the Cycling Sports, it can reach out to all the districts in India. The formation of cycling clubs in every district will act as an entry point of S4I in that district.");
        tp7.addQuestion(q);

        faq.addTopic(tp7);
        Topic tp8 = new Topic();
        tp8.setName("Sustainability");

        q = new Question();
        q.setQuestion("How is S4I plan to compete /overcome the existing private / Government schools of the district?");
        q.setAnswer("S4I do not intent to compete with other schools as S4I will set up schools where there are no quality schools at present in a given district. S4I also recognizes the absence of good schools throughout India in rural India.");
        tp8.addQuestion(q);

        q = new Question();
        q.setQuestion("What is the plan to sustain in the long run?");
        q.setAnswer("S4I will work with the community to find ways and means to take over and run the school eventually. At present we expect the community to have the expertise in 15 years time to mature and run the school. But such an arrangement shall be reviewed on case to case.");
        tp8.addQuestion(q);

        q = new Question();
        q.setQuestion("How schools for India plan to overcome the local disturbances like local private schools’ mischievous activities?");
        q.setAnswer("S4I will engage with other stake holders in the district and ensure that the operations are carried out in mutual trust and good will.");
        tp8.addQuestion(q);

        q = new Question();
        q.setQuestion("What is the future of S4I once the 15 years contract signed by the corporate ends?");
        q.setAnswer("S4I will work with the community to find ways and means to take over and run the school eventually. At present we expect the community to have the expertise in 15 years time to mature and run the school.");
        tp8.addQuestion(q);

        q = new Question();
        q.setQuestion("What if the Government or any other private party has opened a school near the S4I school, and the students move out and join that school for various reasons, how will S4I tackle this and what measures are there to sustain in the competition?");
        q.setAnswer("S4I will work with the government and other agencies to identify the location of a school in a given district. We believe that with the current need there will be under capacity for the next 25 years and hence there won’t be any need for planning for over capacity.");
        tp8.addQuestion(q);

        q = new Question();
        q.setQuestion("Most of the village schools now get aid from local NGO and they do run successfully with it, how do S4I view this and what opportunity is there for S4I to still start a schools there?");
        q.setAnswer("S4I operations in a district will be in supplemental in nature and not intent to gain market share or compete with existing players. S4I will target 2% of the population and there are more work to do in the coming year as the capacity to serve is limited by any organization. With Indian population to touch 1.6 Billion in coming years, S4I project will cater to a very small section of the population, but will act as a bench mark for others to follow.");
        tp8.addQuestion(q);

        faq.addTopic(tp8);
        Topic tp9 = new Topic();
        tp9.setName("Project scope");

        q = new Question();
        q.setQuestion("What is the scope of Schools for India project?");
        q.setAnswer("Schools for India will build and run 6000 schools across India, with focus on rural areas to provide best in class infrastructure and education to the people of rural India.");
        tp9.addQuestion(q);

        q = new Question();
        q.setQuestion("What is the coverage of the school?");
        q.setAnswer("The school will have Kinder Garten to Secondary school, with total 3000 students in three shifts.");
        tp9.addQuestion(q);

        q = new Question();
        q.setQuestion("How large is the school?");
        q.setAnswer("Each school will be built over an area of 20 Acres, with built up area of 140,000 Sq Ft.");
        tp9.addQuestion(q);

        q = new Question();
        q.setQuestion("Why build large schools instead of small schools?");
        q.setAnswer("Based on our studies we find that maintaining a large school is much easier than maintaining many small schools. By providing large and well managed facility will attract the right teaching talent than small schools.");
        tp9.addQuestion(q);

        q = new Question();
        q.setQuestion("How can a school be built in 90 days?");
        q.setAnswer("We are currently working on the Just in Time (JIT) model and creating the Bill of Material and automated supply chain solution which will enable us to build the school using pre fabricated components.");
        tp9.addQuestion(q);

        faq.addTopic(tp9);
        Topic tp10 = new Topic();
        tp10.setName("Student Enrollment");

        q = new Question();
        q.setQuestion("What is the target student population for enrollment?");
        q.setAnswer("Schools for India will target bottom most 2% of the population in terms of economic status. The students from such families will be identified during the secondary and primary research that will be conducted in the district.");
        tp10.addQuestion(q);

        q = new Question();
        q.setQuestion("How do you identify the students who needs free education in a location?");
        q.setAnswer("The primary research which includes door to door survey by Schools for India, district team before the commencement of the project will help in identifying the beneficiary families and the children from such families; hence at the start of the academic year the students will be identified and given admission.");
        tp10.addQuestion(q);

        q = new Question();
        q.setQuestion("How many students will be there in a class?");
        q.setAnswer("Each of the class will have up to 22 students. The special children school will have all the possible students from that area.");
        tp10.addQuestion(q);

        q = new Question();
        q.setQuestion("Will S4I target individual sponsorship for a school? If yes how?");
        q.setAnswer("The support for a school in a district will be done on case to case basis, for example if the S4I project is delayed due to any reason then S4I will explore the possibility of engaging and supporting any of the private or government school in the district.");
        tp10.addQuestion(q);

        q = new Question();
        q.setQuestion("What will be the tuition fee for the students?");
        q.setAnswer("We are expecting that 60% of the students shall be given free admission, 20% shall have government support and 20% shall be on payment basis. This percentage will vary from district to district. We are expecting that the 60% free tuition shall be paid by the community over a period of time.");
        tp10.addQuestion(q);

        faq.addTopic(tp10);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
