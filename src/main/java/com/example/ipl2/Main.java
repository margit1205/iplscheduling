package com.example.ipl2;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {

    public static ArrayList<Match> allMatches(HashMap<Integer, Team> teams) {
        ArrayList<Match> matches = new ArrayList<>();
        int middleIndex = (teams.size() / 2) - 1;
        ArrayList<ArrayList<Match>> eachTeamMatch = new ArrayList<>();
        for(int i=0;i<2;i++) {
            for (int j = 0; j < teams.size() - 1; j++) {
                ArrayList<Match> m1 = new ArrayList<>();
                for (int k = j + 1; k < teams.size(); k++) {
                    Match match = new Match();
                    match.setTeam1(teams.get(j));
                    match.setTeam2(teams.get(k));
                    match.setPlayed(false);
                    if(i==0){
                        match.setVenue(teams.get(j).getHomeGround());
                    }else{
                        match.setVenue(teams.get(k).getHomeGround());
                    }

                    m1.add(match);
                }
                eachTeamMatch.add(m1);
            }
        }
        for (int i = 0; i < eachTeamMatch.size(); i++) {
            if (middleIndex < eachTeamMatch.get(i).size()) {
                matches.add(eachTeamMatch.get(i).get(middleIndex));
                eachTeamMatch.get(i).remove(middleIndex);
            }
        }
        for (int i = 0; i < eachTeamMatch.size(); i++) {
            for (int j = 0; j < eachTeamMatch.get(i).size(); j++) {
                matches.add(eachTeamMatch.get(i).get(j));
            }
        }
//        System.out.println(eachTeamMatch);
        return matches;
    }

    private static Calendar dateIncrementer(Date currentDate, int numberOFDaysToIncrement) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.DATE, numberOFDaysToIncrement);
        return cal;
    }

    private static void scheduler(ArrayList<Match> matches, HashMap<Integer,Team> teams, Date startDate) {
        ArrayList<Match> scheduleMatches = new ArrayList<>();
        ArrayList<Team> busyTeams = new ArrayList<>();
        Date date = startDate;

        int totalMatches = matches.size();
        int weekendCount = 0;
        int count = 0;
        while (totalMatches > 0) {
            for (Match match : matches) {
                if (match.getPlayed() == false) {
                    if (!match.getTeam1().getPlaying() && !match.getTeam2().getPlaying()) {
                        match.setPlayed(true);
                        match.setMatchDate(date);
                        match.getTeam1().setPlaying(true);
                        busyTeams.add(match.getTeam1());
                        match.getTeam2().setPlaying(true);
                        busyTeams.add(match.getTeam2());
                        scheduleMatches.add(match);
                        totalMatches--;
                        break;
                    }
                }
            }
            // System.out.println(date.getDay()+" "+busyTeams);

            if (date.getDay() == 0) {
                weekendCount++;
                count++;
                if (weekendCount < 2) {
                    continue;

                } else {
                    if (!busyTeams.isEmpty()) {
                        if (busyTeams.size() > 4) {
                            busyTeams.get(0).setPlaying(false);
                            busyTeams.get(1).setPlaying(false);
                            busyTeams.get(2).setPlaying(false);
                            busyTeams.get(3).setPlaying(false);
                            busyTeams.remove(0);
                            busyTeams.remove(0);
                            busyTeams.remove(0);
                            busyTeams.remove(0);
                        }
                    }
                    weekendCount = 0;

                }
            }

             else if (date.getDay() == 1) {
                    if (!busyTeams.isEmpty()) {
                        if (busyTeams.size() > 4) {
                            busyTeams.get(0).setPlaying(false);
                            busyTeams.get(1).setPlaying(false);
                            busyTeams.get(2).setPlaying(false);
                            busyTeams.get(3).setPlaying(false);
                            busyTeams.remove(0);
                            busyTeams.remove(0);
                            busyTeams.remove(0);
                            busyTeams.remove(0);
                        }
                    }
                }

                    else {
                        if (date.getDay() == 6 ) {
                            weekendCount++;
                            //count++;
                            if (weekendCount < 2) {
                                continue;
                            } else {
                                if (!busyTeams.isEmpty()) {


                                    if (busyTeams.size() > 2 && count >= 1) {
                                        busyTeams.get(0).setPlaying(false);
                                        busyTeams.get(1).setPlaying(false);
                                        busyTeams.remove(0);
                                        busyTeams.remove(0);
                                    } else {
                                        if (!busyTeams.isEmpty()) {

                                            if (count >= 1 && busyTeams.size() >= 2) {
                                                busyTeams.get(0).setPlaying(false);
                                                busyTeams.get(1).setPlaying(false);
                                                busyTeams.remove(0);
                                                busyTeams.remove(0);
                                            }
                                        }
                                    }

                                }
                                weekendCount = 0;
                            }

                        }
                        else{
                            if(!busyTeams.isEmpty()) {
                                if(count>=1&&busyTeams.size()>=2){
                                    busyTeams.get(0).setPlaying(false);
                                    busyTeams.get(1).setPlaying(false);
                                    busyTeams.remove(0);
                                    busyTeams.remove(0);
                                }

                            }
                        }
                    }


               // System.out.println(date.getDay() + " " + busyTeams);

                count++;
                date = dateIncrementer(date, 1).getTime();


                //System.out.println(date.getDay() + " " + busyTeams);
                for (Match match : scheduleMatches) {

                    System.out.println(match.getTeam1().getTeamName() + " vs " + match.getTeam2().getTeamName() + " " + match.getMatchDate() + " at " + match.getVenue());

                }
            }
            System.out.println(scheduleMatches.size());
        }

            public static void main (String[]args){
                Scanner scan = new Scanner(System.in);
                HashMap<Integer,Team> teamInfo = new HashMap<>();
                ArrayList<Match> matches = new ArrayList<>();

                System.out.println("Welcome to ipl scheduling portal\n\n");
                System.out.println("Enter number of team");
                int n = scan.nextInt();
                scan.nextLine();


                for (int i = 0; i < n; i++) {

                    Team team = new Team();
                    System.out.println("Enter team name" + "  "+(i + 1));
                    String teamname = scan.nextLine();
                    System.out.println("Enter team venue" + "  "+(i + 1));
                    String teamvenue = scan.nextLine();
                    team.setTeamName(teamname);
                    team.setHomeGround(teamvenue);
                    team.setPlaying(false);
                    teamInfo.put(i,team);

                }

                //date
                Date startDate = null;
                System.out.println("Starting date of tournament dd/mm/yyyy");
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    String date = scan.nextLine();
                    startDate = sdf.parse(date);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Program finished");
                }
                matches = allMatches(teamInfo);
               // System.out.println(matches);

                scheduler(matches,teamInfo,startDate);


            }

        }








