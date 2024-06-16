// Q2
/*You have a data of participants in an event, create a dummy list of 50 Candidates
(23 Sports and 27 Academic candidates) as show below,
 
Candidate Type
Candidate Name
Academic Candidate
Ajay
Sports Candidate
Vijay
Sports Candidate
Anil
Sports Candidate
Sunil
 
a. Generate a String (only names) of 10 candidate with comma separated
b. Pass them to a method for processing (create a method called process)
c. Process method with sort (implement sorting algorithm) the received name is ascending order and print the names.
d. Separate string needs to be generated one of Academic candidate and other for Sports candidate.? */

import java.util.Arrays;
public class Candidate {
    private String type;
    private String name;

    public Candidate(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        Candidate[] candidates = {
                new Candidate("Academic Candidate", "Ajay"),
                new Candidate("Sports Candidate", "Vijay"),
                new Candidate("Sports Candidate", "Anil"),
                new Candidate("Sports Candidate", "Sunil"),
                new Candidate("Academic Candidate", "Avinash"),
                new Candidate("Sports Candidate", "Ramesh"),
                new Candidate("Academic Candidate", "Priya"),
                new Candidate("Sports Candidate", "Suresh"),
                new Candidate("Academic Candidate", "Suman"),
                new Candidate("Academic Candidate", "Gita"),
                new Candidate("Sports Candidate", "Manoj"),
                new Candidate("Academic Candidate", "Deepa"),
                new Candidate("Sports Candidate", "Kiran"),
                new Candidate("Academic Candidate", "Rajesh"),
                new Candidate("Sports Candidate", "Mahesh"),
                new Candidate("Academic Candidate", "Neha"),
                new Candidate("Academic Candidate", "Amit"),
                new Candidate("Sports Candidate", "Arun"),
                new Candidate("Sports Candidate", "Prakash"),
                new Candidate("Academic Candidate", "Pooja"),
                new Candidate("Sports Candidate", "Kunal"),
                new Candidate("Academic Candidate", "Sneha"),
                new Candidate("Academic Candidate", "Sanjay"),
                new Candidate("Sports Candidate", "Dinesh"),
                new Candidate("Academic Candidate", "Nisha"),
                new Candidate("Sports Candidate", "Vinod"),
                new Candidate("Academic Candidate", "Meena"),
                new Candidate("Sports Candidate", "Alok")
        };

        Candidate[] selectedCandidates = Arrays.copyOfRange(candidates, 0, 10);
        StringBuilder namesString = new StringBuilder();
        for (int i = 0; i < selectedCandidates.length; i++) {
            namesString.append(selectedCandidates[i].getName());
            if (i < selectedCandidates.length - 1) {
                namesString.append(",");
            }
        }
        System.out.println("Selected names: " + namesString.toString());

        String[] selectedNames = new String[selectedCandidates.length];
        for (int i = 0; i < selectedCandidates.length; i++) {
            selectedNames[i] = selectedCandidates[i].getName();
        }
        process(selectedNames);


        StringBuilder academicNames = new StringBuilder();
        StringBuilder sportsNames = new StringBuilder();

        for (Candidate candidate : candidates) {
            if (candidate.getType().equals("Academic Candidate")) {
                academicNames.append(candidate.getName()).append(",");
            } else if (candidate.getType().equals("Sports Candidate")) {
                sportsNames.append(candidate.getName()).append(",");
            }
        }

        if (academicNames.length() > 0) {
            academicNames.deleteCharAt(academicNames.length() - 1);
        }
        if (sportsNames.length() > 0) {
            sportsNames.deleteCharAt(sportsNames.length() - 1);
        }

        System.out.println("Academic candidate names: " + academicNames.toString());
        System.out.println("Sports candidate names: " + sportsNames.toString());
    }


    public static void process(String[] names) {
        int n = names.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (names[j].compareTo(names[j + 1]) > 0) {
                    // Swap names[j] and names[j+1]
                    String temp = names[j];
                    names[j] = names[j + 1];
                    names[j + 1] = temp;
                }
            }
        }

        StringBuilder sortedNames = new StringBuilder();
        for (int i = 0; i < names.length; i++) {
            sortedNames.append(names[i]);
            if (i < names.length - 1) {
                sortedNames.append(",");
            }
        }
        System.out.println("Sorted names: " + sortedNames.toString());
    }
}
