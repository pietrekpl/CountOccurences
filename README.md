Recruitment Task
Application for counting words, and process results into text file, excel file, and zip file

How use it ?
1. Clone your repository into IDE
 
2. In application in Main class fill constans :
    - final String FILE; ---> this is path to testing file in .txt where should be on your pc
    - final String OUTPUT_TXT ---> This is path, where output file should appear in your PC saved in .txt extension
    - FileOutputStream EXCEL_FILE_INPUT_STREAM ---> This is path, where output should appear in your PC saved in .xlsx extension
    - FileOutputStream ZIP_OUTPUT_STREAM  ---> This is path, where output should appear in your PC saved in .zip extension
3. Run the application
      - if entry file size will be greater than 5Mb, or entry file extension will be different than .txt program will exit
      - in other scanarios application will generate 3 files with destination chosen by user
