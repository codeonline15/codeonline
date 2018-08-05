/*

Sometimes we do not need all the methods in a heavy / costly object.
In such cases, we can use light objects which are proxy for the costly ones until we need all methods of the actual obj
these light objects are called proxy objects

also needed for providing a sophisticated means of accessing and referencing objects running in other processes, on other machines

Example:
Image viewer program
  must be able to list all photo objects,
  but the photo objects must not be loaded into memory until they are required to be rendered.
Java RMI stub objects


Intent
The intent of this pattern is to provide a placeholder for an object to control references to it.
control access to object

Implementation:
Subject: to be implemented by actual and proxy subject
The interface must be implemented by the proxy as well so that the proxy can be used in any location where the RealSubject can be used.
Proxy: maintains a reference to actual subject
       controls access to actual subj and creation and deletion also sometimes
actual subject


Virtual Proxies: delaying the creation and initialization of expensive objects until needed
Remote Proxies: providing a local representation for an object that is in a different address space
Protection Proxies: by giving access to some objects while denying access to others.
Smart References: providing a sophisticated access to certain objects such as tracking the number of references to an object and denying access if a certain number is reached, 
                as well as loading an object from database into memory on demand.



*/


interface Image {
  public void showImage();
}


class ImageProxy implements Image {
  private String imageFilePath;
  private Image proxifiedImage;
  
  public ImageProxy(String imageFilePath) {
    this.imageFilePath= imageFilePath;  
  }
  
  @Override
  public void showImage() {
    proxifiedImage = new HighResolutionImage(imageFilePath);
    proxifiedImage.showImage();
  }

}

class HighResolutionImage implements Image {

  public HighResolutionImage(String imageFilePath) {
    loadImage(imageFilePath);
  }

  private void loadImage(String imageFilePath) {
    System.out.println("loading high res image: " + imageFilePath );
  }

  @Override
  public void showImage() {
    System.out.println("showing highResolutionImage");
  }
}

public class ProxyPattern {
  public static void main(String[] args) {
    Image highResolutionImage1 = new ImageProxy("sample/veryHighResPhoto1.jpeg");
    Image highResolutionImage2 = new ImageProxy("sample/veryHighResPhoto2.jpeg");
    Image highResolutionImage3 = new ImageProxy("sample/veryHighResPhoto3.jpeg");

    highResolutionImage1.showImage();
    
    Image highResolutionImageNoProxy1 = new HighResolutionImage("sample/veryHighResPhoto1.jpeg");
    Image highResolutionImageNoProxy2 = new HighResolutionImage("sample/veryHighResPhoto2.jpeg");
    Image highResolutionImageBoProxy3 = new HighResolutionImage("sample/veryHighResPhoto3.jpeg");
    
    highResolutionImageNoProxy2.showImage();
  }
}
