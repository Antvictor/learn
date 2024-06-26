package antvictor.study.jvm;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author exccedy
 * @date 2022/10/16
 **/
public class MyClassLoaderTest {

    public static class MyClassLoader extends ClassLoader {


        private String classPath;

        public MyClassLoader(String classPath) {
            this.classPath = classPath;

        }

        // 打破双亲委派
        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            synchronized (getClassLoadingLock(name)) {
                // First, check if the class has already been loaded
                Class<?> c = findLoadedClass(name);
                if (c == null) {
                    long t0 = System.nanoTime();

                    // If still not found, then invoke findClass in order
                    // to find the class.
                    long t1 = System.nanoTime();

                    if (!name.startsWith("antvictor.study.jvm")) {
                        c = getParent().loadClass(name);
                    } else {
                        c = findClass(name);
                    }

                    // this is the defining class loader; record the stats
//                    sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
//                    sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
//                    sun.misc.PerfCounter.getFindClasses().increment();
                }
                return c;
            }
        }


        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            byte[] data = loadByte(name);
            return defineClass(name, data, 0, data.length);
        }

        private byte[] loadByte(String name) {
//            name = name.replaceAll("\\.", "/");
            name = name.replace('.', File.separatorChar).concat(".class");
            try {
                FileInputStream fis = new FileInputStream(classPath + "/" + name);
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                byte[] data = new byte[fis.available()];
                fis.read(data);
                fis.close();
                return data;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        private byte[] getBytes(String name) {
//            name = name.replaceAll("\\.", "/");
           String  file = classPath + File.separatorChar +
                   name.replace('.', File.separatorChar).concat(".class");
            try(
                    FileInputStream in = new FileInputStream(file);
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    ) {
                int len;
                byte[] bytes = new byte[2048];
                while ((len = in.read(bytes)) != 1) {
                    out.write(bytes, 0 , len);
                }
                return out.toByteArray();
            } catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }


    public static void main(String[] args) throws Exception {
        MyClassLoader myClassLoader = new MyClassLoader("/Users/exccedy/Downloads");
        Class<?> c = myClassLoader.loadClass("antvictor.study.jvm.User");
        c.newInstance();
        System.out.println("----------");
        System.out.println(c.getClassLoader().getClass().getName());

    }

}
