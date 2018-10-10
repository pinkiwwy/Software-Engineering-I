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
	 * The user's option of displaying the report or generate short report.
	 */
	// private static boolean report;
	private static String report;
	/**
	 * The input file.
	 */
	private static File file;
	/**
	 * The input scanner
	 */
	private static Scanner input;

	public static void main(String[] args) {
		if(args.length == 0) {
			parse();
		}
		if(args.length == 1) {
			String s = args[0];
			file = new File(s);
			parseFile(file);
		}
		shuffle = true;
		if(args.length == 2 && args[1].equals("off")) {
			shuffle = false;
		}
		// next: find numSeat, algorithm choice, report choice
		Plurality plurality = new Plurality(numSeat, ballots, candidates);
		STV stv = new STV(numSeat, ballots, candidates);
		numBallots = ballots.size();
		Voting.shuffle(shuffle);
		// identify the algorithm to be used
		if(alg.equals("p")) {
			plurality.runAlgorithm();
			plurality.generateReport();
		}else if(alg.equals("s")) {
			int quota = stv.calculateDQ(numBallots, numSeat);
			stv.runAlgorithm(quota);
			stv.generateReport();
		}
		// print the report or short report to the terminal or not
		if(report.equals("y")) {
			// print out report
			File o = new File ("output.txt");
			try {
				Scanner output = new Scanner(o);
				while(output.hasNextLine()){
					System.out.println(output.nextLine());
				}
			}
			catch(FileNotFoundException e) {
				System.out.println("No output file available.");
			}
		} else if (report.equals("sr")){
			// print out short report
			File o = new File ("output.txt");
			try {
				Scanner output = new Scanner(o);
				while(output.hasNextLine()){
					// System.out.println(output.nextLine());
				}
			}
			catch(FileNotFoundException e) {
				System.out.println("No output file available.");
			}
		}
	}

	/**
	 * This method processes the command line inputted file it to be an ArrayList of ballots.
	 */
	public static void parseFile(File f) {
		Scanner file_scanner;
		do{
			try{
				file_scanner = new Scanner(f);
				break;
			}
			catch(FileNotFoundException e){
				System.out.println("File not found, please try again.");
			}
		}while(true);
		//parse the csv file
		file_scanner.useDelimiter(",");
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
	 * This method prompts for input file and reads in the csv file and process it to be an ArrayList of ballots.
	 */
	public static void parse(){
		input = new Scanner(System.in);
		System.out.println("Please enter the file name of the csv file.");
		Scanner file_scanner;
		do{
			try{
				String s = input.nextLine();
				file = new File(s);
				file_scanner = new Scanner(file);
				break;
			}
			catch(FileNotFoundException e){
				System.out.println("File not found, please try again.");
			}
		}while(true);
		input.close();
		//parse the csv file
		file_scanner.useDelimiter(",");
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
}
