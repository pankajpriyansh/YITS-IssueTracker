package com.yash.yits.dao.hibernate;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.yash.yits.dao.IssueDao;
import com.yash.yits.domain.Project;

import com.yash.yits.domain.ApplicationTeamMember;
import com.yash.yits.domain.Issue;
import com.yash.yits.domain.Member;

import com.yash.yits.form.MemberForm;
import com.yash.yits.domain.Application;
import com.yash.yits.domain.ApplicationIssuePriority;
import com.yash.yits.domain.Issue;

@Repository
public class IssueDaoImpl implements IssueDao {

	@Autowired
	private SessionFactory sessionFactory;

	public List<Project> getProjectNames() {
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Project.class)
				
				.setProjection(Projections.projectionList()
					      .add(Projections.property("id"), "id")
					      .add(Projections.property("name"), "name"))
				.setResultTransformer(Transformers.aliasToBean(Project.class));
		List<Project> projects= criteria.list();
		System.out.println("in dao "+projects);
		return projects;
	}

	
	public List<Issue> getUnassignedIssues() {
		
		
		/*Criteria criteria=session.createCriteria(Issue.class);
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(Restrictions.isNull("a"));
		criteria.add(disjunction);*/
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Issue.class)
				.setProjection(Projections.projectionList()
						.add( Projections.property("summary"), "summary")
						.add(Projections.property("applicationIssueType"),"applicationIssueType")
						.add(Projections.property("project"),"project")					
											
						.add(Projections.property("applicationIssuePriority"),"applicationIssuePriority"))
						.add(Restrictions.isNull("assignedUser"))
				.setResultTransformer(Transformers.aliasToBean(Issue.class));
		List<Issue> unassignedIssueList=criteria.list();
		List<Issue> list=new ArrayList<Issue>();
		Iterator<Issue> iterator=unassignedIssueList.iterator();
		return unassignedIssueList;

}
	public List<Issue> getDefaultIssues(Timestamp date1, Timestamp date2) {
		
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createSQLQuery("SELECT * FROM Issue WHERE created_Date_Time BETWEEN '"+date1+"' AND '"+date2+"'");
		List<Issue> issueList=new ArrayList<Issue>();
		List<Object> issues=query.list();
		
		for (Object object : issues) {
			
			Issue issue=(Issue)object;
			System.out.println(issue);
			issueList.add(issue);
		}
		
		return issueList;

	}




	public void createIssue(Issue issue,Long createdBy) {
		Session session=sessionFactory.getCurrentSession();
		int createdBy1=findMemberId(createdBy);
		ApplicationTeamMember applicationTeamMember=new ApplicationTeamMember();
		applicationTeamMember.setId(createdBy1);
		issue.setCreatedBy(applicationTeamMember);
		session.save(issue);
		
		
	}
	
	public int findMemberId(Long memberId){
		Session session=sessionFactory.getCurrentSession();
		
		Criteria criteria=session.createCriteria(ApplicationTeamMember.class);
		criteria.setProjection(Projections.projectionList()
			    .add(Projections.property("id"), "id"))
				.setResultTransformer(Transformers.aliasToBean(ApplicationTeamMember.class));
		Criteria criteria1=criteria.createCriteria("member");
		criteria1.add(Restrictions.eq("memberId", memberId));
		
		System.out.println(criteria.uniqueResult());
		
		
		ApplicationTeamMember applicationTeamMember=(ApplicationTeamMember)criteria.uniqueResult();
		int id=applicationTeamMember.getId();
		return id ;
		
	}

	public void getAllSelectFields(Project project, MemberForm member) {
		System.out.println("In DAO for all select fields "+project.getId()+" "+member.getMemberId());
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(ApplicationIssuePriority.class)
		.setProjection(Projections.projectionList()
			      .add(Projections.property("id"), "id")
			      .add(Projections.property("type"), "type"))
		.setResultTransformer(Transformers.aliasToBean(ApplicationIssuePriority.class));
		List<ApplicationIssuePriority> applicationIssuePriority=criteria.list();
		
		
		System.out.println("ApplicationIssuePriority "+applicationIssuePriority);
		/* -------------------------------*/
		
		Criteria criteria2 = session.createCriteria(Project.class)
				.setProjection(Projections.projectionList()
					      .add(Projections.property("id"), "id")
					      .add(Projections.property("name"), "name")
					      .add(Projections.property("projects"), "projects"))
				.setResultTransformer(Transformers.aliasToBean(Application.class));
		
		List<Application> application = criteria2.list();
		Iterator<Application> iterator = application.iterator();
		Application application1 = new Application();
		Project project2= new Project();
		while (iterator.hasNext()) {
			application1 = (Application) iterator.next();
			
		}
		System.out.println("Application "+application1.getId());
		//System.out.println("Application "+application.getId());

	}

}
