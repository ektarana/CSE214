
/**
* Ekta Rana 
* 111030624 
* HW#1 
* Recitation section: 08 
* Recitation TA: Michael Rizzo 
*/
import java.util.InputMismatchException;
import java.util.Scanner;

public class ResidenceHallManager {
	/**
	 * prints the menu
	 */
	public static void printMenu() {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("Menu:");
		System.out.println("A) Add a student");
		System.out.println("R) Remove a student");
		System.out.println("S) Swap Students");
		System.out.println("M) Move Student");
		System.out.println("F) Select Floor");
		System.out.println("C) Copy Floor");
		System.out.println("P) Print Current Floor");
		System.out.println("W) Write Up Student");
		System.out.println("Q) Quit");
		System.out.println("Select an option: ");
	}
	/**
	 * swaps the students at the given positions
	 * 
	 * @param s1floor
	 *            student 1 floor
	 * @param s1room
	 *            student 1 room
	 * @param s2floor
	 *            student 2 floor
	 * @param s2room
	 *            student 2 room
	 * @throws IllegalArgumentException
	 *             if the arguments are not legal
	 * @throws FullFloorException
	 *             setStudent must throw FullFloor
	 */
	public static void swapStudents(Floor s1floor, int s1room, Floor s2floor, int s2room)
			throws IllegalArgumentException, FullFloorException {
		Student temp;
		temp = s1floor.getStudent(s1room);
		s1floor.setStudent(s2floor.getStudent(s2room), s1room);
		s2floor.setStudent(temp, s2room);
	}

	/**
	 * moves a student to the given position from the given location
	 * 
	 * @param sFloor
	 *            the source floor
	 * @param sRoom
	 *            the source room
	 * @param destFloor
	 *            destination floor
	 * @param destRoom
	 *            destination room
	 * @throws InputMismatchException
	 *             thrown when the inout is not of the correct type
	 */
	public static void moveStudent(Floor sFloor, int sRoom, Floor destFloor, int destRoom)
			throws IllegalArgumentException, InputMismatchException, FullFloorException {
		// make sure args are legal
		if (sRoom < 0 || sRoom > sFloor.CAPACITY - 1 || destRoom < 0 || destRoom > sFloor.CAPACITY - 1) {
			throw new IllegalArgumentException();
		}
		// make sure the source room is not empty
		if (sFloor.getStudent(sRoom) == null) {
			System.out.println("There is no one in this room to move.");
		}
		// if floor is full, no one can be moved there
		if (sFloor.getStudent(sFloor.CAPACITY - 1) != null) {
			throw new FullFloorException();
		}
		// this is a valid pos
		if (destRoom > 0 && destFloor.getStudent(destRoom - 1) != null) {
			destFloor.shiftRight(destRoom); // shifts right in the destination arr
			destFloor.setStudent(sFloor.getStudent(sRoom), destRoom);
			sFloor.shiftLeft(sRoom); // shifts left in the source array
			sFloor.setStudentAmount((sFloor.getStudentAmount()) - 1);
			destFloor.setStudentAmount(destFloor.getStudentAmount() + 1);
		} else if (destRoom == 0 && destFloor.getStudent(destRoom) != null) { 																			
			destFloor.shiftRight(destRoom); // shifts right in the destination arr
			destFloor.setStudent(sFloor.getStudent(sRoom), destRoom); 
			sFloor.shiftLeft(sRoom); // shifts left in the source array
			sFloor.setStudentAmount((sFloor.getStudentAmount()) - 1); 
			destFloor.setStudentAmount(destFloor.getStudentAmount() + 1);
		} else if (destRoom == 0 && destFloor.getStudent(destRoom) == null) {
			destFloor.setStudent(sFloor.getStudent(sRoom), destRoom);
			sFloor.shiftLeft(sRoom); // shifts left in the source array
			sFloor.setStudentAmount((sFloor.getStudentAmount()) - 1); 
			destFloor.setStudentAmount(destFloor.getStudentAmount() + 1); 
		}
	}

	public static void main(String[] args) throws IllegalArgumentException, FullFloorException {
		Scanner input = new Scanner(System.in);
		String selection;
		Floor floor1 = new Floor();
		Floor floor2 = new Floor();
		Floor floor3 = new Floor();
		boolean run = true;
		System.out.println("Welcome to RockStar Rez, the second worst housing management System at SBU.");
		Floor currentFloor = floor1;

		while (run) {
			printMenu();
			selection = input.next();
			selection = selection.toUpperCase();

			switch (selection) {
			case "A": {
				try {
					System.out.println("Add a student:");
					System.out.println("Please enter a name: ");
					String name = input.next();
					System.out.println("Please enter an id number: ");
					int id = input.nextInt();
					System.out.println("Please enter a spot number: ");
					int position = input.nextInt() - 1;
					input.nextLine();
					Student addThisGuy = new Student(name, id, 0);
					currentFloor.addStudent(addThisGuy, position);
				} catch (FullFloorException e) {
					System.out.println("This floor is full");
				} catch (IllegalArgumentException e) {
					System.out.println("Invalid input. Try again.");
				} catch (InputMismatchException e) {
					System.out.println("Invalid input. Try again.");
				}
				break;
			}
			case "R": {
				try {
					System.out.println("Remove a student:");
					System.out.println("Please select a student's room number: ");
					int position = input.nextInt() - 1;
					input.nextLine();
					System.out.println(currentFloor.getStudent(position).getName() + " has been removed");
					currentFloor.removeStudent(position);
				} catch (IllegalArgumentException e) {
					System.out.println("Invalid input. Try again.");
				} catch (EmptyFloorException e) {
					System.out.println("This floor is empty");
				} catch (InputMismatchException e) {
					System.out.println("Invalid input. Try again.");
				} catch (NullPointerException e) {
					System.out.println("No one in this room. Try again.");
				}
				break;
			}
			case "S": {
				try {
					System.out.println("Please enter the Student 1 floor: ");
					int s1floor = input.nextInt();
					System.out.println("Please enter the Student 1 room: ");
					int s1room = input.nextInt() - 1;
					System.out.println("Please enter the Student 2 floor: ");
					int s2floor = input.nextInt();
					System.out.println("Please enter the Student 2 room: ");
					int s2room = input.nextInt() - 1;
					input.nextLine();
					if (s1floor == 1 && s2floor == 1) {
						swapStudents(floor1, s1room, floor1, s2room);
						System.out.println(floor1.getStudent(s1room).getName() + " and "
								+ floor1.getStudent(s2room).getName() + " have been swapped.");
					} else if (s1floor == 1 && s2floor == 2) {
						swapStudents(floor1, s1room, floor2, s2room);
						System.out.println(floor1.getStudent(s1room).getName() + " and "
								+ floor2.getStudent(s2room).getName() + " have been swapped.");
					} else if (s1floor == 1 && s2floor == 3) {
						swapStudents(floor1, s1room, floor3, s2room);
						System.out.println(floor1.getStudent(s1room).getName() + " and "
								+ floor3.getStudent(s2room).getName() + " have been swapped.");
					} else if (s1floor == 2 && s2floor == 1) {
						swapStudents(floor2, s1room, floor1, s2room);
						System.out.println(floor2.getStudent(s1room).getName() + " and "
								+ floor1.getStudent(s2room).getName() + " have been swapped.");
					} else if (s1floor == 2 && s2floor == 2) {
						swapStudents(floor2, s1room, floor2, s2room);
						System.out.println(floor2.getStudent(s1room).getName() + " and "
								+ floor2.getStudent(s2room).getName() + " have been swapped.");
					} else if (s1floor == 2 && s2floor == 3) {
						swapStudents(floor2, s1room, floor3, s2room);
						System.out.println(floor2.getStudent(s1room).getName() + " and "
								+ floor2.getStudent(s2room).getName() + " have been swapped.");
					} else if (s1floor == 3 && s2floor == 1) {
						swapStudents(floor3, s1room, floor1, s2room);
						System.out.println(floor3.getStudent(s1room).getName() + " and "
								+ floor1.getStudent(s2room).getName() + " have been swapped.");
					} else if (s1floor == 3 && s2floor == 2) {
						swapStudents(floor3, s1room, floor2, s2room);
						System.out.println(floor3.getStudent(s1room).getName() + " and "
								+ floor2.getStudent(s2room).getName() + " have been swapped.");
					} else if (s1floor == 3 && s2floor == 3) {
						swapStudents(floor3, s1room, floor3, s2room);
						System.out.println(floor3.getStudent(s1room).getName() + " and "
								+ floor3.getStudent(s2room).getName() + " have been swapped.");
					} else {
						System.out.println("Sorry, invalid choice. Try again.");
					}
				} catch (InputMismatchException e) {
					System.out.println("Invalid input. Try again.");
				} catch (FullFloorException e) {
					System.out.println("This floor is full.");
				} catch (IllegalArgumentException e) {
					System.out.println("Invalid input. Try again.");
				}
				break;
			}
			case "M": {
				try {
					System.out.println("Please enter the source floor: ");
					int sFloor = input.nextInt();
					System.out.println("Please enter the source room: ");
					int sRoom = input.nextInt() - 1;
					System.out.println("Please enter the destination floor: ");
					int destFloor = input.nextInt();
					System.out.println("Please enter the destination room: ");
					int destRoom = input.nextInt() - 1;
					input.nextLine();
					if (sFloor == 1 && destFloor == 1) {
						moveStudent(floor1, sRoom, floor1, destRoom);
					} else if (sFloor == 1 && destFloor == 2) {
						moveStudent(floor1, sRoom, floor2, destRoom);
					} else if (sFloor == 1 && destFloor == 3) {
						moveStudent(floor1, sRoom, floor3, destRoom);
					} else if (sFloor == 2 && destFloor == 1) {
						moveStudent(floor2, sRoom, floor1, destRoom);
					} else if (sFloor == 2 && destFloor == 2) {
						moveStudent(floor2, sRoom, floor2, destRoom);
					} else if (sFloor == 2 && destFloor == 3) {
						moveStudent(floor2, sRoom, floor3, destRoom);
					} else if (sFloor == 3 && destFloor == 1) {
						moveStudent(floor3, sRoom, floor1, destRoom);
					} else if (sFloor == 3 && destFloor == 2) {
						moveStudent(floor3, sRoom, floor2, destRoom);
					} else if (sFloor == 3 && destFloor == 3) {
						moveStudent(floor3, sRoom, floor3, destRoom);
					} else {
						System.out.println("Sorry, invalid choice. Try again.");
					}
				} catch (IllegalArgumentException e) {
					System.out.println("Invalid input. Try again.");
				} catch (InputMismatchException e) {
					System.out.println("Invalid input. Try again.");
				} catch (NullPointerException e) {
				} catch (FullFloorException e) {
					System.out.println("Floor is full, you can't move a student here. Try again.");
				}
				break;
			}
			case "F": {
				try {
					System.out.println("Select a floor 1-3 :");
					int num = input.nextInt();
					input.nextLine();
					switch (num) {
					case 1:
						currentFloor = floor1;
						break;
					case 2:
						currentFloor = floor2;
						break;
					case 3:
						currentFloor = floor3;
						break;
					default:
						System.out.println("Invalid input.");
						break;
					}
				} catch (IllegalArgumentException e) {
					System.out.println("Invalid input. Try again.");
				}
				break;
			}
			case "C": {
				try {
					System.out.println("Please enter the source floor (1-3): ");
					int source = input.nextInt();
					System.out.println("Please enter the destination floor (1-3): ");
					int dest = input.nextInt();
					input.nextLine();
					if (source == 1 && dest == 2) {
						floor2 = floor1.clone();
						System.out.println("Floor 1 Copied to Floor 2.");
					} else if (source == 1 && dest == 3) {
						floor3 = floor1.clone();
						System.out.println("Floor 1 Copied to Floor 3.");
					} else if (source == 2 && dest == 1) {
						floor1 = floor2.clone();
						System.out.println("Floor 2 Copied to Floor 1.");
					} else if (source == 2 && dest == 3) {
						floor3 = floor2.clone();
						System.out.println("Floor 2 Copied to Floor 3.");
					} else if (source == 3 && dest == 1) {
						floor1 = floor3.clone();
						System.out.println("Floor 3 Copied to Floor 3.");
					} else if (source == 3 && dest == 2) {
						floor2 = floor3.clone();
						System.out.println("Floor 3 Copied to Floor 2.");
					} else {
						System.out.println("Sorry, choose a number between 1-3!!");
					}
				} catch (InputMismatchException e) {
					System.out.println("Invalid input. Try again.");
				}
				break;
			}
			case "P": {
				if (currentFloor == floor1) {
					System.out.println("Floor 1");
				} else if (currentFloor == floor2) {
					System.out.println("Floor 2");
				} else {
					System.out.println("Floor 3");
				}
				currentFloor.printFloor();
				break;
			}
			case "W": {
				try {
					System.out.println("Please enter student name: ");
					String name = input.next();
					currentFloor.writeup(name);
					// System.out.println(currentFloor.getStudent(position).getName());

				} catch (EmptyFloorException e) {
					System.out.println("This floor is empty");
				}
				break;
			}
			case "Q": {
				System.out.println("Thanks for using RockStar Rez!!!!!");
				run = false;
				break;
			}
			default: {
				System.out.println("Invalid input.");
				break;
			}

			}// end of switch
		} // close while
		input.close();
	}
}