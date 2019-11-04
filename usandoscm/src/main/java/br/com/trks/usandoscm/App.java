package br.com.trks.usandoscm;

import java.util.ResourceBundle;

import org.eclipse.jgit.lib.Repository;

import br.edu.ifpb.scm.api.AbstractFactory;
import br.edu.ifpb.scm.api.SCM;
import br.edu.ifpb.scm.api.factories.ScmGitFactory;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ResourceBundle resource = ResourceBundle.getBundle("scm");
        String dir = System.getProperty("java.io.tmpdir") + resource.getString("dir");
        String url = resource.getString("url.repo");

        try {

            AbstractFactory factory = new ScmGitFactory();
            SCM scm = factory.createScm();
            scm.setDir(dir).setUrl(url);
            br.edu.ifpb.scm.api.Repository repository = scm.buildRepository();

            repository.getVersions().forEach(version -> {
                System.out.println("\n ---- Commits Info ---- ");
                System.out.println("Commit Date: " + version.getCommitDate());
                System.out.println("Commit Hashcode: " + version.getHashCode());
                System.out.println("Message:" + version.getMessage());
                System.out.println("Author: " + version.getAuthor().getName());
                System.out.println("Email: " + version.getAuthor().getEmail());
                System.out.println("\n");
            });

        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}
