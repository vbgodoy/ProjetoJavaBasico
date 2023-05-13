import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import builders.StudentsBuilder;
import entities.Student;

public class Main {

    // Método utilizado no Exercício 1 e 2.
    public static void studentsApprovalEvaluator(Student student, String approvalIndex) {

        float averageGrade = (student.getTestOne() + student.getTestTwo() + student.getTestThree()) / 3;
        
        if ((averageGrade >= 7.0f) && (approvalIndex.equals("Aprovados"))) {
            System.out.printf("%d - %s : Média = %.1f\n", 
                                                student.getCode(), 
                                                student.getName(), 
                                                averageGrade);
        }

        if ((averageGrade < 7.0f) && (approvalIndex.equals("Reprovados"))) {
            float cutlineGradeDeviation = 7.0f - averageGrade;
            System.out.printf("%d - %s : Média = %.1f (Faltou = %.1f)\n", 
                                                student.getCode(), 
                                                student.getName(), 
                                                averageGrade, 
                                                cutlineGradeDeviation);
        }
    }

    // Método utilizado no Exercício 3.
    public static void maxGradeStudents(Student student) {

        if ((student.getTestOne() == 10.0f) || (student.getTestTwo() == 10.0f) || (student.getTestThree() == 10.0f)) {
            System.out.printf("%d - %s\n", student.getCode(), student.getName());
        }

    }

    // Método utilizado no Exercício 4.
    public static void minGradeStudents(Student student, float minGrade) {

        ArrayList<Float> studentGrades = new ArrayList<>() {{
            add(student.getTestOne());
            add(student.getTestTwo());
            add(student.getTestThree());
        }}; 

        float minStudentGrade = Collections.min(studentGrades);

        if (minStudentGrade == minGrade) {
            System.out.printf("%d - %s : Nota = %.1f\n", student.getCode(), student.getName(), minStudentGrade);
        }

    }

    // Método utilizado no Exercício 5.
    public static float maxGradeOfStudent(Student student) {

        ArrayList<Float> studentGrades = new ArrayList<>() {{
            add(student.getTestOne());
            add(student.getTestTwo());
            add(student.getTestThree());
        }}; 

        float maxGrade = Collections.max(studentGrades);

        return maxGrade;

    }

        // Método utilizado no Exercício 6.
        public static float minGradeOfStudent(Student student) {

            ArrayList<Float> studentGrades = new ArrayList<>() {{
                add(student.getTestOne());
                add(student.getTestTwo());
                add(student.getTestThree());
            }}; 
    
            float minGrade = Collections.min(studentGrades);
    
            return minGrade;
    
        }
    

    public static void main(String[] args) {
        var allStudents = StudentsBuilder.getAllStudents();

        // Agora vamos as atividades
        /*

        1. Recupere da lista os alunos que passaram de ano (nota minima 7.0).
            - Exiba os dados nesse formato: <código> - <nome> : Média = <nota>
        2. Recupere da lista os alunos que não passaram de ano.
            - Exiba os dados nesse formato: <código> - <nome> : Média = <media> (Faltou = <nota_faltante>)
        3. Traga os alunos que tiraram a nota máxima (nota 10).
            - Exiba os dados nesse formato: <código> - <nome>
        4. Traga o aluno que tirou a menor nota, em caso de notas iguais, traga ambos os alunos.
            - Exiba os dados nesse formato: <código> - <nome> : Nota = <nota>
        5. Faça uma lista com top 3 notas de alunos. Em caso de notas iguais coloque todos na mesma posição.
            - Ex:
                1º - Fulano : Nota = 10.0;
                   - Beltrano : Nota = 10.0;
                2º - Joãozinho : Nota = 9.0;
                3º - Mariazinha : Nota = 8.9;
            - Exiba os dados nesse formato: <posicao> - <nome> : Nota = <nota>
        6. Faça uma lista com as 3 menores notas de alunos. Em caso de notas iguais coloque todos na mesma posição. Exemplo igual a anterior
            - Exiba os dados nesse formato: <posicao> - <nome> : Nota = <nota>
        7. Monte a média de todos os alunos e exiba em tela ordenando da maior para a menor nota.
            - Exiba os dados nesse formato: <posicao> - <código> - <nome> : Média = <nota>

         */

        // 1. Recupere da lista os alunos que passaram de ano (nota minima 7.0).
        var studentsList = new ArrayList<>(allStudents);

        studentsList.forEach((i) -> studentsApprovalEvaluator(i, "Aprovados"));

        System.out.println("--------------------------");

        // 2. Recupere da lista os alunos que não passaram de ano.
        
        studentsList.forEach((i) -> studentsApprovalEvaluator(i, "Reprovados"));

        System.out.println("--------------------------");

        // 3. Traga os alunos que tiraram a nota máxima (nota 10).

        studentsList.forEach((i) -> maxGradeStudents(i));

        System.out.println("--------------------------");

        // 4. Traga o aluno que tirou a menor nota, em caso de notas iguais, traga ambos os alunos.

        ArrayList<Float> allGrades = new ArrayList<>();

        studentsList.forEach((i) -> {
            allGrades.add(i.getTestOne());
            allGrades.add(i.getTestTwo());
            allGrades.add(i.getTestThree());
        });

        float minGrade = Collections.min(allGrades);
        studentsList.forEach((i) -> minGradeStudents(i, minGrade));

        System.out.println("--------------------------");

        // 5. Faça uma lista com top 3 notas de alunos. Em caso de notas iguais coloque todos na mesma posição.

        HashMap<String, Float> maxGradeMap = new HashMap<String, Float>();
        ArrayList<Float> listOfMaxGrades = new ArrayList<>();

        studentsList.forEach((i) -> {
            maxGradeMap.put(i.getName(), maxGradeOfStudent(i));
            listOfMaxGrades.add(maxGradeOfStudent(i));
        });

        Collections.sort(listOfMaxGrades);
        Collections.reverse(listOfMaxGrades);

        LinkedHashMap<String, Float> sortedMaxGradeMap = new LinkedHashMap<>();
        for (float grade : listOfMaxGrades) {
            for (Map.Entry<String, Float> entry : maxGradeMap.entrySet()) {
                if (entry.getValue().equals(grade)) {
                    sortedMaxGradeMap.put(entry.getKey(), grade);
                };
            }
        }

        int j = 1;
        float num = -1;
        for (Map.Entry<String, Float> entry : sortedMaxGradeMap.entrySet()) {
            if (entry.getValue() != num) {
                System.out.printf("%dº - %s : Nota = %.1f\n", j, entry.getKey(), entry.getValue());
                num = entry.getValue();
                j++;
            } else {
                System.out.printf("   - %s : Nota = %.1f\n", entry.getKey(), entry.getValue());
            }

            if (j > 3) {
                break;
            } 
        }

        System.out.println("--------------------------");

        // 6. Faça uma lista com as 3 menores notas de alunos. Em caso de notas iguais coloque todos na mesma posição. Exemplo igual a anterior.

        HashMap<String, Float> minGradeMap = new HashMap<String, Float>();
        ArrayList<Float> listOfMinGrades = new ArrayList<>();

        studentsList.forEach((i) -> {
            minGradeMap.put(i.getName(), minGradeOfStudent(i));
            listOfMinGrades.add(minGradeOfStudent(i));
        });

        Collections.sort(listOfMinGrades);

        LinkedHashMap<String, Float> sortedMinGradeMap = new LinkedHashMap<>();
        for (float grade : listOfMinGrades) {
            for (Map.Entry<String, Float> entry : minGradeMap.entrySet()) {
                if (entry.getValue().equals(grade)) {
                    sortedMinGradeMap.put(entry.getKey(), grade);
                }
            }
        }
        
        int k = 1;
        num = -1;
        for (Map.Entry<String, Float> entry : sortedMinGradeMap.entrySet()) {
            if (entry.getValue() != num) {
                System.out.printf("%dº - %s : Nota = %.1f\n", k, entry.getKey(), entry.getValue());
                num = entry.getValue();
                k++;
            } else {
                System.out.printf("   - %s : Nota = %.1f\n", entry.getKey(), entry.getValue());
            }

            if (k > 3) {
                break;
            } 
        }

        System.out.println("--------------------------");

        // 7. Monte a média de todos os alunos e exiba em tela ordenando da maior para a menor nota.

        ArrayList<Float> averageGradeList = new ArrayList<Float>();
        HashMap<String, Float> averageGradeMap = new HashMap<String, Float>();
        HashMap<String, Integer> nameIDMap = new HashMap<String, Integer>();

        studentsList.forEach((i) -> {
            float averageGrade = (i.getTestOne() + i.getTestTwo() + i.getTestThree()) / 3;
            averageGradeList.add(averageGrade);
            averageGradeMap.put(i.getName(), averageGrade);
            nameIDMap.put(i.getName(), i.getCode());
        });

        Collections.sort(averageGradeList);
        Collections.reverse(averageGradeList);

        LinkedHashMap<String, Float> sortedAverageGradeMap = new LinkedHashMap<String, Float>();
        for (float grade : averageGradeList) {
            for (Map.Entry<String, Float> entry : averageGradeMap.entrySet()) {
                if (entry.getValue().equals(grade)) {
                    sortedAverageGradeMap.put(entry.getKey(), grade);
                }
            }
        }

        LinkedHashMap<String, Integer> sortedNameIDMap = new LinkedHashMap<String, Integer>();
        for (String name : sortedAverageGradeMap.keySet()) {
            for (Map.Entry<String, Integer> entry : nameIDMap.entrySet()) {
                if (entry.getKey().equals(name)) {
                    sortedNameIDMap.put(name, entry.getValue());
                }
            }
        }

        int n = 1;
        for (Map.Entry<String, Float> entry : sortedAverageGradeMap.entrySet()) {
            Integer code = sortedNameIDMap.values().stream().toList().get(n - 1);
            System.out.printf("%dº - %d - %s : Média = %.1f\n", n, code, entry.getKey(), entry.getValue());
            n++;
        }

    }
}
