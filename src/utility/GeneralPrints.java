package utility;

import user.*;
import chain.*;
import prototype.*;
/**
 * Created by HP on 03/09/2017.
 */
public class GeneralPrints {
    GeneralScanners sc = new GeneralScanners();

    public int printMenu() {
        System.out.println(
                "Menu:\n" +
                        "1 - Cadastrar Usuario\n" +
                        "2 - Cadastrar Recurso\n" +
                        "3 - Marcar Alocacao de Recurso\n" +
                        "4 - Confirmar Alocacao\n" +
                        "5 - Concluir Alocacao\n" +
                        "6 - Consultar Usuario\n" +
                        "7 - Consultar Recurso\n" +
                        "8 - Relatorio Completo\n" +
                        "0 - Sair\n");
        return sc.scannerInt.nextInt();
    }

    public Person printRegisterUser() {
        Person p;

        System.out.println("Digite o nome do usuario:\n");
        String name = sc.scannerString.nextLine();
        System.out.println("Digite o cpf do usuario:\n");
        String cpf = sc.scannerString.nextLine();
        System.out.println("Digite o e-mail do usuario:\n");
        String email = sc.scannerString.nextLine();
        System.out.println("Digite o tipo do usuario:\n" +
                "0 = Aluno de Graduação\n" +
                "1 = Aluno de Mestrado\n" +
                "2 = Aluno de Doutorado\n" +
                "3 = Professor\n" +
                "4 = Pesquisador\n" +
                "5 = Administrador\n");
        int type = sc.scannerInt.nextInt();
        switch (type) {
            case 0:
                p = new UndergraduateStundent(name, email, cpf);
                break;
            case 1:
                p = new MasterStudent(name, email, cpf);
                break;
            case 2:
                p = new PhDStudent(name, email, cpf);
                break;
            case 3:
                p = new Professor(name, email, cpf);
                break;
            case 4:
                p = new Reseacher(name, email, cpf);
                break;
            case 5:
                p = new Admin(name, email, cpf);
                break;
            default:
                p = null;
        }
        System.out.println("Cadastro realizado com sucesso!!\n");
        return p;
    }
    public Handler printRegisterResource() {
        Factory f = new Factory();
        System.out.println(
                "Digite a identificacao do recurso:" +
                        "0 = Laboratorio\n" +
                        "1 = Auditorio\n" +
                        "2 = Sala de Aula\n" +
                        "3 = Projetor\n");
        int type = sc.scannerInt.nextInt();
        return f.getHandler(type);
    }
    public void printBookResource(DataBase db) {
        System.out.println("Digite o cpf do usuário");
        String cpf = sc.scannerString.nextLine();
        System.out.println("Digite a identificacao do recurso:" +
                        "0 = Laboratorio\n" +
                        "1 = Auditorio\n" +
                        "2 = Sala de Aula\n" +
                        "3 = Projetor\n");
        int type = sc.scannerInt.nextInt();
        db.getChain().handleRequest(type, db.getPerson(cpf));
    }

    public void printConfirmBook(DataBase db) {
        System.out.println("Digite o ID do recurso para confirmar alocacao:\n");
        int ID = sc.scannerInt.nextInt();
        db.getResource(ID).getState().nextState(db.getResource(ID));
    }

    public void printCompleteBook(DataBase db) {
        System.out.println("Digite o ID do recurso para concluir alocacao:\n");
        int ID = sc.scannerInt.nextInt();
        db.getResource(ID).getState().nextState(db.getResource(ID));
    }

    public void printSearchUser(DataBase db) {
        System.out.println("Digite o CPF do usuario que deseja consultar:\n");
        String cpf = sc.scannerString.nextLine();
        System.out.println(db.getPerson(cpf).toString());
    }

    public void printResourceUser(DataBase db) {
        System.out.println("Digite o ID do recurso que deseja consultar:\n");
        int ID = sc.scannerInt.nextInt();
        System.out.println(db.getResource(ID).toString());
    }

    public void printReport(DataBase db) {
        System.out.println(
                "Relatorio:" +
                    "\n\tNumero de Usuarios: " + db.getPersonList().size() +
                        "\n\t\tAluno de Graduacao: " + db.getQuantityUndergraduateStudent() +
                        "\n\t\tAluno de Mestrado: " + db.getQuantityMasterStudent() +
                        "\n\t\tAluno de Doutorado: " + db.getQuantityPhDStudent() +
                        "\n\t\tProfessor:" + db.getQuantityProfessor() +
                        "\n\t\tPesquisador:" + db.getQuantityResearcher() +
                    "\n\tNumero de Recursos: " + db.getResourceList().size() +
                        "\n\t\tAuditorio: " + db.getQuantityAuditory() +
                        "\n\t\tSala de Aula: " + db.getQuantityClassRoom() +
                        "\n\t\tLaboratorio: " + db.getQuantityLaboratory() +
                        "\n\t\tProjetor: " + db.getQuantityProjector());
    }
}
