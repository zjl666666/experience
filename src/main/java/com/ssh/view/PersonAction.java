/**
 * 
 */
package com.ssh.view;

import java.util.List;

import javax.annotation.Resource;

import com.common.domain.Paging;
import com.common.view.DefaultAction;
import com.ssh.domain.Person;
import com.ssh.service.PersonService;

/**
 * @Description: TODO
 * @author zjl
 * @date 2014年12月30日 下午3:50:53 
 */
public class PersonAction extends DefaultAction {

	@Resource(name = "personManyHibernateServiceImpl")
	private PersonService personService;
	
	private List<Person> persons;
	
	public String index(){
		Paging<Person> paging = new Paging<Person>();
		paging.setCurrentPageNo(0);
		Paging<Person> result = personService.searchPersons(paging);
		this.persons = result.getResultList();
		this.request.setAttribute("persons", persons);
		return "list";
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
	
	
	
}
