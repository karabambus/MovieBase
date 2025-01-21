   import Controller.UserController;
   import DAL.NamedRepository;
   import DAL.RepositoryFactory;
import DAL.Repository;
   import Exceptions.RegistrationException;
   import Utilities.SQLUtils;

   import java.util.ArrayList;
   import java.util.List;

    public class Test {
        public static void main(String[] args) {
            UserController controller = new UserController();
            try {
                System.out.println(controller.loginUser("Marin", "P4$$w0rd"));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
