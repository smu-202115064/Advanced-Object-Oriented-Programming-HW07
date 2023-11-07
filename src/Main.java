public class Main {
    public static void main(String[] args) {
        PasswordInfo passwordInfo;
        PasswordDAO dao = new PasswordDAOImpl();

        // 데이터를 DB에 추가
        System.out.println("--- inserting...");
        passwordInfo = new PasswordInfo("https://www.smu.ac.kr", "smu", "abcde");
        dao.insert(passwordInfo);
        passwordInfo = new PasswordInfo("https://www.smu2.ac.kr", "smu2", "abcde");
        dao.insert(passwordInfo);

        // 데이터가 제대로 추가되었는지 모든 데이터를 출력해서 확인(SELECT로 확인)
        // [PS] main() 함수에서 별도의 DB connection을 만들어 결과를 확인해보려하면 Dead Lock 현상이 발생한다. 해결 방법 불명.
        System.out.println("--- finding all...");
        for (PasswordInfo pi : dao.findAll()) {
            System.out.println("reading... " + pi);
        }

        // 두 번째 데이터의 id인 "smu2"를 "smu1"으로 수정.
        System.out.println("--- updating...");
        passwordInfo = dao.findAll().get(1);
        passwordInfo.setId("smu1");
        dao.update(passwordInfo);

        // 해당 데이터가 제대로 수정되었는지 데이터를 출력해서 확인(SELECT로 확인)
        System.out.println("--- see if updated...");
        passwordInfo = dao.findByKey(passwordInfo.getUrl());
        if (passwordInfo != null) {
            System.out.println(passwordInfo);
        }

        // "https://www.smu2.ac.kr" 데이터 삭제(key로 삭제)
        System.out.println("--- deleting...");
        dao.delete("https://www.smu2.ac.kr");

        // 전체 데이터 출력해서 데이터가 제대로 삭제되었는지 확인
        System.out.println("--- finding all after deleting...");
        for (PasswordInfo pi : dao.findAll()) {
            System.out.println("reading... " + pi);
        }
    }
}
