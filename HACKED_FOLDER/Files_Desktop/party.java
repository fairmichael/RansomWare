/**
 Party should be a class.
 People objects should be in the party class.
 Everyone in the party has a count of people they know and they don't know.
 When a people object gets added, everyone in the party gets their "know" and "don't know" attributes updated.
 */

import java.util.ArrayList;

public class Person
{
    private String info;
    private int peopleKnowInParty;
    private int peopleNotKnowInParty;
    private int idNumber;
    private int k1;
    private int k2;
    private ArrayList<Integer> peopleKnown;
    
    public Person(String info, int idNumber, int k1, int k2)
    {
        this.info = info;
        this.idNumber = idNumber;
        this.peopleKnowInParty = 0;
        this.peopleNotKnowInParty = 0;
        this.k1 = k1;
        this.k2 = k2;
        for(int c = 0; c < info.length(); c++)
        {
            if(info.charAt(c) == '1')
                peopleKnown.add(c);
        }
    }
    
    public String getInfo()
    {
        return info;
    }
    
    public int getK1()
    {
        return k1;
    }
    
    public int getK2()
    {
        return k2;
    }
    
    public int getIdNumber()
    {
        return idNumber;
    }
    
    public boolean doKnow(Person person)
    {
        if(peopleKnown.contains(person.getIdNumber()))
            return true;
        return false;
    }
    
    public void incPeopleKnown()
    {
        this.peopleKnowInParty++;
    }
    
    public void incPeopleNotKnown()
    {
        this.peopleNotKnowInParty++;
    }
}

public class Party
{
    private ArrayList<Person> list;
    
    public Party()
    {
        list = new ArrayList<>();
    }
    
    public Party(Party party)
    {
        list = new ArrayList<>();
        for(Person person : party.getList())
        {
            list.add(new Person(person.getInfo(), person.getIdNumber(), person.getK1(), person.getK2()));
        }
    }
    
    public void addPerson(Person person)
    {
        for(Person p : list)
        {
            if(p.doKnow(person))
            {
                p.incPeopleKnown();
                person.incPeopleKnown();
            }
            else
            {
                p.incPeopleNotKnown();
                person.incPeopleNotKnown();
            }
        }
        list.add(person);
    }
    
    public ArrayList<Person> getList()
    {
        return list;
    }
    
    public int getPartySize()
    {
        return list.size();
    }
}

public class main
{
    public static void main(String[] args)
    {
        
    }
    
    public static ArrayList<Integer> createParty(String[] list, int n, int k1, int k2, Party party)
    {
        if(list.length == 0 || n == 0)
            return party;

        if(countK1(list[n-1]) < k1 || list[n-1].length - countK1(list[n-1]) < k2)
            return createParty(list, n-1, k1, k2, party);
        
        
    }
    
    private static int countK1(String candidate)
    {
        int count = 0;
        for(int i = 0; i < candidate.length(); candidate++)
        {
            if(candidate.charAt(i) == '1')
                count++;
        }
        return count;
    }
    
    private static boolean belongs(ArrayList<Integer> people, String person, int k1, int k2)
    {
        if(countK1(person) < k1)
            return false;
        if(person.length() - countK1(person) < k2)
            return false;
        int k1count = 0;
        for(int i = 0; i < person.length(); i++)
        {
            if(person.charAt(i))
                }
    }
}
