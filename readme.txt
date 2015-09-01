************************************************************************************
##SOME INFORMATION FOR THOSE WHO WANTS TO RUN THIS PROJECT
####Although I have my doubts about existence of such a person :)
************************************************************************************
**If you downloading project from repository, you have to include some js and css 
libraries to make it work properly.**

Make directory "lib" in "src/main/webapp/resources/css" 
and "src/main/webapp/resources/js" directories.

Download if necessary and add "ui.jqgrid.css" 
to "src/main/webapp/resources/css/lib" 
and "jquery.jqGrid.src.js", "jquery.jqGrid.min.js", 
grid.locale-en.js to "src/main/webapp/resources/js/lib".

These directories were excluded from commits (.gitignore).

*************************************************************************************
All necessary dependencies for java you may find in pom.xml.
*************************************************************************************
Settings for jdbc and email registration confirmation  
are located in "src\main\resources\properties" directory.
*************************************************************************************
There may be some issues with libraries located within Tomcat lib folder 
and jdbc connector jar if different environment are used.