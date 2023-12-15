package antvictor.study.design.simplefactory;

/**
 * @author Antvictor
 * @date 2023/12/15
 **/
public class CourseFactory {
    public static ICourse createCourse(Class<? extends ICourse> clazz) {
      /*  if (courseType == null) {
            return null;
        }
        if (courseType.equalsIgnoreCase("python")) {
            return new PythonCourse();
        } else if (courseType.equalsIgnoreCase("java")) {
            return new JavaCourse();
        } else {
            return null;
        }*/

        try {
            if (null != clazz) {
                return clazz.newInstance();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
