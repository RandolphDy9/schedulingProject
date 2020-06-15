package project;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Project {
	private String projectName;
	private String startDate;
	private String endDate;
	private int totalDuration;
	private ArrayList<Task> tasks;
	
	public Project(String projectName, String startDate, ArrayList<Task> tasks) {
		this.projectName = projectName;
		this.startDate = startDate;
		this.tasks = new ArrayList<Task>();
	}
	
	public void addTask(Task newTask) {
		this.tasks.add(newTask);
	}
	
	public void setEndDate(String startDate) {
		if (this.getTotalDuration() == 0) {
			this.endDate = startDate;
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Calendar c = Calendar.getInstance();
			
			try{
			   //Setting the date to the given date
			   c.setTime(sdf.parse(startDate));
			}catch(ParseException e){
				e.printStackTrace();
			 }
			   
			c.add(Calendar.DAY_OF_MONTH, this.getTotalDuration());  
			this.endDate = sdf.format(c.getTime());
		}
	}
	
	public void setTotalDuration(Task newTask) {
		this.totalDuration += newTask.getDuration();
	}
	
	public int getTotalDuration() {
		return this.totalDuration;
	}
	
	public String getProjectName() {
		return this.projectName;
	}
	
	public String getStartDate() {
		return this.startDate;
	}
	
	public ArrayList<Task> getTasks() {
		return this.tasks;
	}
	
	public String openProjectList(int index, ArrayList<Task> tasks) {
		return "[" + (index + 1) + "] - " + this.projectName + " (Tasks: " + tasks.size() + ")";
	}
	
	public String allProjectsDisplay() {
		return "\nProject name       : " + this.projectName + "\n"
			   + "Project start date : " + this.startDate + "\n"
			   + "Project end date   : " + (this.endDate == null ? this.startDate : this.endDate) + "\n"
			   + "Total Duration     : " + this.totalDuration + " day(s)\n";
	}
	
	public void displayTasksForProject(ArrayList<Task> newTasks) { 
		if (newTasks.size() > 0) {
			for (Task task: newTasks) {
				System.out.println(task.perTaskDisplay());
			}
		} else {
			System.out.println("There are no tasks for this project.");
		}
	}
	
}
