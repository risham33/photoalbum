/**
 * @version 1.0
 */

package cs213.photoAlbum.model;

import java.util.Comparator;

/**
 * for sorting tag
 * @author Jasmine Feng and Risham Chokshi
 */
public class tagSort implements Comparator<Tag>{
	private static final long serialVersionUID = 7526472295622776147L;
	/**
	 * @param cal instace of a tag
	 * @param calsecond instance of the tag
	 * @return int compare of the tag
	 *  
	 * */
	public int compare(Tag cal, Tag calsecond) {
		
		if(cal==null){
			return 1;
		}
		
		else if(calsecond == null)
			return -1;
	
		
				if(cal.getName().equalsIgnoreCase("location")){ // this is location so needs to be first
					//take it to the front
					return -1;
				}
				else if(calsecond.getName().equalsIgnoreCase("location")){ // this is location so needs to be first
					//take it to the front
					return 1;
				}
				else if(cal.getName().equalsIgnoreCase("person")&&calsecond.getName().equalsIgnoreCase("person")!=true){
					return -1;
					
				}
				else if(cal.getName().equalsIgnoreCase("person")&&calsecond.getName().equalsIgnoreCase("person")==true){
					return cal.getDescription().compareTo(calsecond.getDescription());
				}
				else if(cal.getName().equals(calsecond.getName())){
					return cal.getDescription().compareTo(calsecond.getDescription());				}
				else
					return cal.getName().compareTo(calsecond.getName());
				
			
		}
				
			
	}

