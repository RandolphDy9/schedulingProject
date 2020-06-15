package project;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Menu {

	public static void main(String[] args) {

		int option = 0;
		ArrayList<Project> projects = new ArrayList<Project>();
		ArrayList<Task> tasks = new ArrayList<Task>();
		String startDate = null;
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Calendar Schedule for Project Plans.");

		do {
			try {
				System.out.println(
					"\nSelect Option:\n"
					+ "[1] - Create project plan\n"
					+ "[2] - Open a project plan\n"
					+ "[3] - View all projects\n"
					+ "[4] - Exit\n"
					+ "Enter your choice: ");
				option = Integer.parseInt(scan.nextLine());
	        } catch (NumberFormatException e) {
	            continue;
	        }
			
			switch(option) {
				case 1:
					System.out.println("\n ******* Create Project: ******* \n");
					
					String projectName = null;
					Date dateStart = new Date();
					Date currentDate = new Date();
					Scanner scanString = new Scanner(System.in);
					
					try {
						System.out.println("Enter project name:");
						projectName = scan.nextLine();
			        } catch (NumberFormatException e) {
			            continue;
			        }
					
					try {
						boolean checkStart = false;
						System.out.println("\nEnter start date (dd-mm-yyyy):");
						do {
							startDate = scanString.nextLine();
							SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
							format.setLenient(false);
							
							try {
								dateStart = format.parse(startDate);
								
								// Checks if the entered start date is not before the current date.
								if (currentDate.compareTo(dateStart) < 0) {
									checkStart = true;
								} else {
									checkStart = false;
									System.out.println("Start date should not be less than the current date. Please re-enter.");
								}
							} catch (ParseException e) {
								System.out.println("Date should be in dd-mm-yyyy format. Please re-enter.");
								continue;
							}
						} while (!checkStart);
			        } catch (NumberFormatException e) {
			            continue;
			        }
					
					Project project = new Project(projectName, startDate, tasks);
					projects.add(project);
					
					System.out.println("\nProject has been added!");
					break;
				case 2:
					System.out.println("\n ******** Project List: ******** \n");
					
					int counter = 0;
					for (Project perProject : projects) {
			            System.out.println(perProject.openProjectList(counter, perProject.getTasks()));
			            counter++;
			        }
					
					int selected = 1;
					try {
						System.out.println("\nSelect a project to open:");
						selected = Integer.parseInt(scan.nextLine());
			        } catch (NumberFormatException e) {
			            continue;
			        }
					
					// Checks if option is valid.
					if (selected >= 1 && selected < (counter + 1)) {
						int projectNumberIndex = selected - 1;
						System.out.println("\n" + projects.get(projectNumberIndex).getProjectName() + " has been opened!");
						taskManagement(projects.get(projectNumberIndex), projects.get(projectNumberIndex).getTasks(), projectNumberIndex, startDate);
					} else {
						System.out.println("\nInvalid option. Please enter again.");
					}
					break;
				case 3:
					System.out.println("\n ****** Project Details: ****** ");
			        for (Project perProject : projects) {
			            System.out.println(perProject.allProjectsDisplay()); 
			            perProject.displayTasksForProject(perProject.getTasks());
			        }
					break;
				case 4:
					System.out.println("\nAlright. Bye-bye!");
					scan.close();
					break;
				default:
					System.out.println("\nInvalid option. Please enter again.");
					break;
			}
		} while(option != 4);
	}
	
	public static void taskManagement(Project project, ArrayList<Task> tasks, int projectIndex, String startDate) {
		int option = 0;
		Scanner scanOption = new Scanner(System.in);
		
		do {
			try {
				System.out.println(
						"\nTask Management Options (Project: " + project.getProjectName() + ")\n"
						+ "[1] - Create a task\n"
						+ "[2] - View tasks\n"
						+ "[3] - Exit\n"
						+ "Enter your choice: ");
				option = Integer.parseInt(scanOption.nextLine());
	        } catch (NumberFormatException e) {
	            continue;
	        }
			
			switch(option) {
				case 1:
					System.out.println("\n ******* Create A Task: *******");
					
					String taskName = null;
					int duration = 0;
					int dependencyCount = 0;
					Scanner scanString = new Scanner(System.in);
					
					try {
						System.out.println("\nEnter task name:");
						taskName = scanString.nextLine();
			        } catch (NumberFormatException e) {
			            continue;
			        }
					
					try {
						System.out.println("\nEnter the duration for this task (in days):");
						duration = Integer.parseInt(scanOption.nextLine());
			        } catch (NumberFormatException e) {
			        	System.out.println("\nInvalid input!"); 
			            continue;
			        }
					
					try {
						System.out.println("\nEnter the number of dependencies for this task (current number of tasks: " + project.getTasks().size() + "):");
						dependencyCount = Integer.parseInt(scanOption.nextLine());
						if (dependencyCount > project.getTasks().size() || dependencyCount < 0) {
							System.out.println("\nInvalid input! Exceeds number of current tasks"); 
							break;
						}
			        } catch (NumberFormatException e) {
			        	System.out.println("\nInvalid input!"); 
			            continue;
			        }

					Task task = new Task(taskName, duration, dependencyCount);
					project.addTask(task);
					project.setTotalDuration(task);
					project.setEndDate(project.getStartDate());
					
					System.out.println("\nTask has been added!"); 
					break;
				case 2:
					System.out.println("\n ******* Tasks under " + project.getProjectName() + ": ******* \n");
					project.displayTasksForProject(project.getTasks());
					break;
				case 3:
					break;
				default:
					System.out.println("\nInvalid option. Please enter again.");
					break;
			}
		} while(option != 3);
	}

}
