import java.io.*;

public class UploadParser {
    
    private String fileToParse;
    RandomAccessFile raf;
    
    public UploadParser(String s)throws Exception
    {
        fileToParse = s;
        File f = new File(fileToParse);
        if(!f.exists())
            throw new Exception("File Not Exist");
        
        raf = new RandomAccessFile(fileToParse,"r");
    }
    
    private String getFieldSeparator() throws Exception
    {
        raf.seek(0);
        return raf.readLine();
    }
    
    String getFieldValue(String fieldName) throws Exception
    {
        String currentLine,fieldSeparator,toMatch;
        boolean keepReading;
        
        keepReading = true;
        fieldSeparator = getFieldSeparator();
        toMatch = "name=\""+fieldName+"\"";
        raf.seek(0);
        while(keepReading)
        {
            currentLine = raf.readLine();
            if(currentLine==null)
                break;
            if(currentLine.equals(fieldSeparator))
            {
                currentLine = raf.readLine();
                if(currentLine.startsWith("Content-Disposition: form-data;"));
                {
                    if(currentLine.contains(toMatch))
                    {
                        raf.readLine();
                        return raf.readLine();
                    }
                }
            }
        }
        return null;
    }
    
    private String getUploadFileName(String fileField)throws Exception
    {
        String currentLine, fieldSeparator, toMatch;
        boolean keepReading;
        
        keepReading= true;
        fieldSeparator = getFieldSeparator();
        toMatch = "name=\""+ fileField+ "\"";
        
        raf.seek(0); 
        while(keepReading)
        {
            currentLine = raf.readLine();
            if(currentLine == null)//EOF
                break;
            
            if(currentLine.equals(fieldSeparator))
            {//a new field starting ahead
                currentLine = raf.readLine();
                if(currentLine.startsWith("Content-Disposition: form-data;"))
                {
                    if(currentLine.contains(toMatch))
                    {
                        int pos1 = currentLine.lastIndexOf("=");
                        pos1 += 2;
                        int pos2 = currentLine.length() -1;
                        
                        return currentLine.substring(pos1, pos2);
                    }
                }
            }
        }//while
        return null;  
    }
    
    long getFileStartPosition(String fileField) throws Exception
    {
        String currentLine, fieldSeparator, toMatch;
        boolean keepReading;
        
        keepReading= true;
        fieldSeparator = getFieldSeparator();
        toMatch = "name=\""+ fileField+ "\"";
        
        raf.seek(0);
        
        while(keepReading)
        {
            currentLine = raf.readLine();
            if(currentLine == null)//EOF
                break;
            
            if(currentLine.equals(fieldSeparator ))
            {//a new field starting ahead
                currentLine = raf.readLine();
                if(currentLine.startsWith("Content-Disposition: form-data;"))
                {
                    if(currentLine.contains(toMatch))
                    {
                        raf.readLine();//fetch and ignore : Content-Type: .....
                        raf.readLine();//fetch and ignore the empty line
                        return raf.getFilePointer();//file pointer position
                    }
                }
            }
        }//while
        return -1;//not found
    }
    
    
    long getFileEndPosition(long startPosition) throws Exception
    {
        String currentLine, fieldSeparator;
        boolean keepReading;
        long pos;
        
        keepReading= true;
        fieldSeparator = getFieldSeparator();
        
        raf.seek(startPosition);
        
        while(keepReading)
        {
            pos = raf.getFilePointer();
            currentLine = raf.readLine();
            if(currentLine == null)//EOF
                break;
            
            if(currentLine.equals(fieldSeparator))
            {//a new field starting ahead
                return pos - 2; //2 bytes are of new line 
            }
        }//while
        return -1;//not found
    
    }
    
     String extractFile(String fileField) throws Exception
    {
        String targetFileName;
        long pos1, pos2;
        
        targetFileName = getUploadFileName(fileField);
        if(targetFileName != null)
        {
            pos1 = getFileStartPosition(fileField);
            if(pos1 != -1)
            {
                pos2 = getFileEndPosition(pos1);
                if(pos2 != -1)
                {
                    long bytesToRead = pos2-pos1;
                    long i;
                    raf.seek(pos1);
                    String target = "b:\\share\\" + new File(targetFileName).getName();
                    FileOutputStream fos = new FileOutputStream(target);
                    
                    for(i =0; i< bytesToRead; i++)
                    {
                        fos.write(raf.read());
                    }
                    
                    fos.flush();
                    fos.close();
                    
                    return target;
                }//if(pos2
            }//if(pos1
        }//if(targetFileName 
        throw new Exception("File Extraction Failed");
        
    }//extractFile
    
    void close() throws Exception
    {
        raf.close();
        //new File(fileToParse).delete();
    }
}
