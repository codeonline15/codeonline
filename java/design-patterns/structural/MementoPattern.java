/*

Capture internal state of obj at a point and ability to restore to that point later
useful in case of error / system failure
without violating encapsulation

Implementation:
Memento: store internal state of originator

Originator: creates memento obj capturing its internal state,
            use memento obj to restore its cur state

caretaker: responsible for keeping memento
caretaker operates on originator - try to rollback
caretaker calls creatememento meth in originator,
originator creates and saves its internal state and returns obj to caretaker
when needed, caretaker calls setmemento meth in originator to restore

examples:
db transactions
Undo and restore operations in most software -  text editors

Memento class should be accessible only to the Originator object.
use caretaker object for saving and restoring the originator state.
can use Serialization to achieve memento pattern impl that is more generic rather than Memento where every object needs to have it’s own Memento implementation.

can be expensive depending on the amount of state information that has to be stored inside the memento object.

*/

class FileWriterUtil {

  private String fileName;
  private StringBuilder content;
  
  public FileWriterUtil(String file){
    this.fileName=file;
    this.content=new StringBuilder();
  }
  
  @Override
  public String toString(){
    return this.content.toString();
  }
  
  public void write(String str){
    content.append(str);
  }
  
  public Memento save(){
    return new Memento(this.fileName,this.content);
  }
  
  public void undoToLastSave(Object obj){
    Memento memento = (Memento) obj;
    this.fileName= memento.fileName;
    this.content=memento.content;
  }
  
  
  private class Memento{
    private String fileName;
    private StringBuilder content;
    
    public Memento(String file, StringBuilder content){
      this.fileName=file;
      //notice the deep copy so that Memento and FileWriterUtil content variables don't refer to same object
      this.content=new StringBuilder(content);
    }
  }
}

class FileWriterCaretaker {

  private Object obj;
  
  public void save(FileWriterUtil fileWriter){
    this.obj=fileWriter.save();
  }
  
  public void undo(FileWriterUtil fileWriter){
    fileWriter.undoToLastSave(obj);
  }
}

public class MementoPattern {

  public static void main(String[] args) {
    
    FileWriterCaretaker caretaker = new FileWriterCaretaker();
    
    FileWriterUtil fileWriter = new FileWriterUtil("data.txt");
    fileWriter.write("First Set of Data\n");

    System.out.println(fileWriter);
    
    caretaker.save(fileWriter);
    fileWriter.write("Second Set of Data\n");
    
    System.out.println(fileWriter);

    caretaker.undo(fileWriter);
    
    System.out.println(fileWriter);
    
  }

}
