package sample.hello.dao;

import java.util.List;

import sample.hello.bean.Subject;

public interface SubjectMapper {
	/**
	 * 插入主题返回主键
	 * @param subject
	 * @return
	 */
  public int addSubject(Subject subject);

  public Subject getSubjectById(String subjectId);
  
  public List<Subject> getAllSubject();
}
