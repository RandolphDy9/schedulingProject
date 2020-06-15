package project;

public class Task {
	private String name;
	private int duration;
	private int dependencies;
	
	public Task(String name, int duration, int dependencies) {
		this.name = name;
		this.duration = duration;
		this.dependencies = dependencies;
	}
	
	public long getDuration() {
		return this.duration;
	}
	
	public String perTaskDisplay() {
		return "Task name    : " + this.name + "\n"
			 + "Duration     : " + this.duration + " days\n"
		 	 + "Dependencies : " + this.dependencies + " task(s)\n";
	}
}
