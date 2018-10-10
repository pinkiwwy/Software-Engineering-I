import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 * @author Team21
 * The Main class of the Voting System for prompting user input, parsing file and
 * running the corresponding voting algorithm based on user's option.
 *
 */

public class Main{
	/**
	 * The user's choice of shuffle option.
	 */
	private static boolean shuffle;
	/**
	 * The user's option of algorithm.
	 */
	private static String alg;
	/**
	 * The total number of candidates.
	 */
	private static int numCandidate;
	/**
	 * The total number of seats.
	 */
	private static int numSeat;
	/**
	 * The total number of ballots in the csv file.
	 */
	private static int numBallots;
	/**
	 * The ArrayList for storing ballots.
	 */
	private static ArrayList<Ballot> ballots = new ArrayList<Ballot>();
	/**
	 * The ArrayList for storing candidates.
	 */
	private static ArrayList<Candidate> candidates = new ArrayList<Candidate>();
	/**
	 * The user's option of displaying the report or short report.
	 */
	private static String report;
	/**
	 * The input file.
	 */
	private static File file;
	/**
	 * The input scanner
	 */
	private static Scanner input;

	public static void main(String[] args){
		if(args.length == 0) {
			File file = parse();
			parseFile(file);
		}
		if(args.length == 1 || args.length ==2) {
			String s = args[0];
			file = new File(s);
			Scanner file_scanner;
			try {
				file_scanner = new Scanner(file);
				file_scanner.close();
			}
			catch(FileNotFoundException e) {
				System.out.println("File not found, please try again.");
				System.exit(0);
			}
			parseFile(file);
		}
		shuffle = true;
		if(args.length == 2 && args[1].equals("off")){
			shuffle = false;
		}
		//check if numSeat is greater than or equal to number of candidates
		if(numSeat > candidates.size()){
			throw new IllegalArgumentException("the total number of candidates must be greater than or equal to the number of seats .");
		}
		Plurality plurality = new Plurality(numSeat, ballots, candidates);
		STV stv = new STV(numSeat, ballots, candidates);
		numBallots = ballots.size();
		Voting.shuffle(shuffle);
		File z = new File ("short_report.txt");
		if(getAlg().equals("p")) {
			plurality.runAlgorithm();
			plurality.generateReport();
			plurality.generateShortReport();
		}else if(getAlg().equals("s")) {

			int quota = stv.calculateDQ(numBallots, numSeat);
			stv.runAlgorithm(quota);
			stv.generateReport();
			stv.generateShortReport();
		}
		if(report.equals("y")) {
			File o = new File ("output.txt");
			try {
				Scanner output = new Scanner(o);
				while(output.hasNextLine()) {
					System.out.println(output.nextLine());
				}
			}
			catch(FileNotFoundException e) {
				System.out.println("No output file available.");
			}
		}
	}

	/**
	 * This method processes the command line inputted file it to be an ArrayList of ballots.
	 * @param f The file name from command line argument
	 */
	public static void parseFile(File f) {
		Scanner file_scanner;
		do {
			try {
				file_scanner = new Scanner(f);
				break;
			}
			catch(FileNotFoundException e) {
				System.out.println("File not found, please try again.");
			}
		}while(true);
		//parse the csv file
		file_scanner.useDelimiter(",");
		//parsing algorithm option
		String algOption = file_scanner.nextLine();
		if(!algOption.equals("p") && !algOption.equals("s")){
			throw new IllegalArgumentException("algorithm choice must be indicated with either p or s in the first line of the file.");
		}
		setAlg(algOption);
		//parsing numSeat
		String nS = file_scanner.nextLine();
		numSeat = Integer.parseInt(nS);
		//parsing report option
		String r = file_scanner.nextLine();
		if(r.equals("y")){
			report = "y";
		}else if(r.equals("n")){
			report = "n";
		}else{
			throw new IllegalArgumentException("report option in the file must be either y or n");
		}

		String names = file_scanner.nextLine();
		//parsing and creating candidate arraylist
		Scanner name_scanner = new Scanner(names);
		name_scanner.useDelimiter(",");
		while(name_scanner.hasNext()){
			String candidate_name = name_scanner.next();
			int candidate_vote = 0;
			ArrayList<Integer> voteOrder = new ArrayList<Integer>();
			//Candidate constructor
			Candidate cand = new Candidate(candidate_name, candidate_vote, voteOrder);
			candidates.add(cand);
		}
		numCandidate = candidates.size();
		name_scanner.close();
		//parsing and creating ballots
		int ID = 1;
		while(file_scanner.hasNext()){
			String b = file_scanner.nextLine();
			String splitBy = ",";
			String[] ballot_string = b.split(splitBy);
			int[] ballot = new int[numCandidate];
			for (int i = 0; i < ballot.length; i++) {
				ballot[i] = -1;
			}
			for (int i = 0; i < ballot_string.length; i ++){
				if(ballot_string[i].equals(" ") || ballot_string[i].equals("")){
					ballot[i] = -1; // not ranked
				}else{
					ballot[i] = Integer.parseInt(ballot_string[i]);
				}
			}
			Ballot bal = new Ballot(ballot, ID);
			ballots.add(bal);
			ID += 1;
		}
		file_scanner.close();
	}

	/**
	 * This method prompts for input file name. Repeat if file not found
	 * @return a File
	 */
	public static File parse(){
		input = new Scanner(System.in);
		System.out.println("Please enter the file name of the csv file.");
		Scanner file_scanner;
		do{
			try{
				String s = input.nextLine();
				file = new File(s);
				file_scanner = new Scanner(file);
				file_scanner.close();
				return file;

			}
			catch(FileNotFoundException e){
				System.out.println("File not found, please try again.");
			}
		}while(true);
	}
	
	//function below are the getter and setter for alg. For testing in unittest
	/**
	 * This method gets the algorithm option from user.
	 * @return an string of user's alg option
	 */
	public static String getAlg() {
		return alg;
	}
	/**
	 * Set the algorithm option.
	 * @param alg The algorithm option that user chooses.
	 */
	public static void setAlg(String alg) {
		Main.alg = alg;
	}
}
