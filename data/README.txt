Photo Album Project
-------------------

by Risham Chokshi and Jasmine Feng

:)

For testing we used users.txt and test.txt files.
These files are in the same directory where src is in directory called images. 
Ex:
where_project_is_stored\photoalbum\photoalbum22\images\users.txt

All our data with the serialization is stored in data folder in the same directory where src is located for this project

to test, we went in the bin located in the same directory where src is and typed different commands mentioned below

Commands, arguments and Outputs: 

COMMAND LINE MODE: 

To list existing users: 
	java cs213.photoAlbum.simpleview.CmdView listusers 
	Output: 
	<userId1> 
	<userId2> 
	... 
	
	Or: 
	
	no users exist

To add a new user: 

	java cs213.photoAlbum.simpleview.CmdView adduser <user id> "<user name>" 

	Output: 

	created user <user id> with name <user name> 
	
	Or: 
	
	user <user id> already exists with name <user name>

To delete a user: 

	java cs213.photoAlbum.simpleview.CmdView deleteuser <user id> 

	Output: 
	
	deleted user <user id> 
	
	Or: 

	user <user id> does not exist

To login as an existing user: 
	
	java cs213.photoAlbum.simpleview.CmdView login <user id> 

	(view now goes into interactive mode) 
	
	Or: 
	
	user <user id> does not exist


IN INTERACTIVE MODE: 
**for improper arguments, the error below will be printed**
Improperly formatted arguments. 

HELP WITH COMMANDS:

Enter help for a list of commands

To create an album: 
	
	createAlbum "<name>" 

	Output: 

	created album for user <user id>: 
	<name> 

	Or: 

	album exists for user <user id>: 
	<name>
	
	Or:
	Error: albumName cannot be null


To delete an album: 
	
	deleteAlbum "<name>" 
	
	Output: 

	deleted album from user <user id>: 
	<name> 

	Or: 
	
	album does not exist for user <user id>: 
	<name>

	Or:
	Error: albumName cannot be null

To list all albums, with their number of photos and the range of dates that the photos were taken: 

	listAlbums 

	Output: 

	Albums for user <user id>: 
	<name> number of photos: <numberOfPhotos>, <start date> - <end date> 
	... 

	Or: 
	
	No albums exist for user <user id>


To list the photos in an album: (it is ordered according to its last modified time. The oldest one gets ordered first)

	listPhotos "<name>" 

	Output: 
	
	Photos for album <name>: 
	
	<fileName> - <date>	
	... 

	Or: 
	
	Album <albumName> does not exist
	
	Or:
	
	Photos for album <name>: 
	No photos in this album

To add a photo to an album: 

	addPhoto "<fileName>" "<caption>" "<albumName>" 

	Output: 
	
	Added photo <fileName>: 
	<caption> - Album: <albumName> 

	Or: 
	
	Photo <fileName> already exists in album <albumName> 

	Or: 

	Album <albumName> does not exist 
	
	Or: 

	File <fileName> does not exist

	Or:
	
	Error: Last day Modified for "+ filename + " is not correct, Photo is not added
	
	Or:
	
	Error: Date of the file is cannot be parsed
	
	Or:
	
	Error: Cannot get file's path
	
To move a photo from one album to another: 

	movePhoto "<fileName>" "<oldAlbumName>" "<newAlbumName>" 

	Output: 

	Moved photo <fileName>: 
	
	<fileName> - From album <oldAlbumName> to album <newAlbumName> 

	Or: 
	
	Album <albumName> does not exist 
	
	Or: 
	
	Photo <fileName> does not exist in <oldAlbumName>
	
	Or:
	
	Error: Photo <filename> already exists in the album <newAlbumname>  OR: all possible addPhoto method error
	
To remove a photo from an album

	removePhoto "<fileName>" "<albumName>" 

	Output: 
	
	Removed photo: 
	<fileName> - From album <albumName> 

	Or: 
	Album <albumName> does not exist 

	Or: 
	Photo <fileName> is not in album <albumName>

To add a tag to a photo: 

	addTag "<fileName>" <tagType>:"<tagValue>" 

	Output: 

	Added tag: 
	<fileName> <tagType>:<tagValue> 

	Or: 

	Photo <fileName> does not exist 

	Or: 

	Tag already exists for <fileName> <tagType>:<tagValue>

	Or:
	
	Error: tagName needs to be filled
	
	Or:
	
	Error: tagName and tagValue cannot be null
	

To delete a tag from a photo: 

	deleteTag "<fileName>" <tagType>:"<tagValue>" 

	Output: 

	Deleted tag: 
	<fileName> <tagType>:<tagValue> 

	Or: 

	Photo <fileName> does not exist 

	Or: 

	Tag does not exist for <fileName> <tagType>:<tagValue>
	
	Or:
	
	Error: Cannot have a null entry
	
To list photo info: 

	listPhotoInfo "<fileName>" 

	Output: 

	Photo file name: <fileName> 
	Album: <albumName>[,<albumName>]... 
	Date: <date> 
	Caption: <caption> 
	Tags: 
	<tagType>:<tagValue> 
	... 

	Or: 
	Photo <fileName> does not exist

	Or:
	Error: Photo " + filename+ " does not exist, filename cannot be empty
	
	Or:
	Error: Filename given was null

To get photos by Date

	getPhotosByDate MM/dd/yyyy-HH:mm:ss MM/dd/yyyy-HH:mm:ss 

	Output: 
	
	Photos for user <user id> in range <start date> to <end date>: 
	<caption> - Album: <albumName>[,<albumName>]... - Date: <date> 
	...
	
	Or:
	
	Error: No photos added for this user
	
	Or:
	
	Error: No photos were added in this time frame
	
	Or:
	
	Error: improperly formatted dates, please try again
	
	Or:
	
	Error:  <Date> is not a valid entry, please try again

To get photos by tag (there should be , and a space between two tags). Also it outputs photos which has all the tags

	getPhotosByTag <tagType>:"<tagValue>", "<tagValue>"... 

	Output: 

	Photos for user <user id> with tags <search string>: 
	<caption> - Album: <albumName>[,<albumName>]... - Date: <date> 
	...
	
	Or:
	
	Error: No photos exists with these tags
	
To rename Album
	
	renameAlbum "oldAlbumName" "newAlbumName"
	
	Output:
	
	Rename successful Album: oldAlbumName  changed to: newAlbumName

	Or:
	
	Error: in renaming of Album, could not find the album
	
To edit caption of the filename

	recaption "filename" "newCaption"
	
	Output:
	
	Photo filename caption changed to newCaption
	
	Or:
	
	Caption cannot be null
	
	Or:
	
	Caption cannot be empty
	
	Or:
	
	filename does not exist for this user:  ID
	
	Or:
	
	Error: filename does not exist
	
	Or:
	
	Error: Photo filename already has newcaption as its caption
	
	
	
To end the interactive mode (and the session): 

	logout 

If wrong format, an error appears:

Incorrectly formatted inputs, please enter again.

