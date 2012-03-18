package com.MoNeYBaGS_.Leaderboards;

import java.util.ArrayList;

public class trimLeaderboards {
	
	private int count = 0;
	
	private ArrayList<String> trim(String untrimmed)
	{
		ArrayList<String> finals = new ArrayList<String>();
		String temp = "";
		
		for(int i = 1; i < untrimmed.length(); i++)
		{
			if(count == 9)
				break;
			if(untrimmed.charAt(i) == ',' || i == untrimmed.length() - 1)
			{
				temp += " Kills";
				finals.add(temp);
				count++;
				i += 1;
				temp = "";
				continue;
			}
			else if(untrimmed.charAt(i) == '=')
			{
				temp += " - ";
			}
			else
			{
				temp += untrimmed.charAt(i);
			}
		}		
		return finals;
	}
	
	private ArrayList<String> trimCheck(String untrimmed)
	{
		ArrayList<String> finals = new ArrayList<String>();
		String temp = "";
		
		for(int i = 1; i < untrimmed.length(); i++)
		{
			if(count == 20)
				break;
			if(untrimmed.charAt(i) == ',' || i == untrimmed.length() - 1)
			{
				temp = "";
				continue;
			}
			else if(untrimmed.charAt(i) == '=')
			{
				finals.add(temp);
				temp = "";
			}
			else
			{
				temp += untrimmed.charAt(i);
			}
		}		
		return finals;
	}
	
	public ArrayList<String> getTrimmed(String total)
	{
		return trim(total);
	}
	
	public ArrayList<String> getTrimCheck(String total)
	{
		return trimCheck(total);
	}
}
