import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Map;

public class RecordBook {
    public class Session
    {
        String sessionInfo;
        Map <String, Integer> subjectMarks;

        Session(String info, StringTokenizer subjectAndMark)
        {
            this.sessionInfo= new String(info);
            this.subjectMarks = new HashMap<>();

            String subject=subjectAndMark.nextToken();
            int mark=Integer.parseInt(subjectAndMark.nextToken());
            this.subjectMarks.put(subject, mark);
        }

        public void putSubjectAndMark( StringTokenizer subjectAndMark)
        {
            String subject=subjectAndMark.nextToken();
            int mark=Integer.parseInt(subjectAndMark.nextToken());
            this.subjectMarks.put(subject, mark);
        }
    }

    String studentName;
    int group;
    Map <String, Session> sessions ;

    RecordBook(String name, int group)
    {
        this.sessions=new HashMap<>();
        this.studentName=new String(name);
        this.group=group;
    }
    public void addSession(StringTokenizer subjectAndMarks)
    {
        String courseAndNum=subjectAndMarks.nextToken()+subjectAndMarks.nextToken();

        if(this.sessions.containsKey(courseAndNum)) {
            this.sessions.get(courseAndNum).putSubjectAndMark(subjectAndMarks);
        }
        else
        {
            this.sessions.put(courseAndNum, new Session(courseAndNum, subjectAndMarks));
        }
    }

    public void OutPut(Writer out) throws IOException {
        String[] str = new String[100];
        int count = 0;


        str[count++] = new String(this.studentName + " group#" + this.group + ":");
        for (String info : this.sessions.keySet()) {
            str[count++] = new String("  Course#" + info.charAt(0) + " Session#" + info.charAt(1) + ":");
            for (String subject : this.sessions.get(info).subjectMarks.keySet()) {
                str[count] = new String();
                str[count++] = "    " + subject + ' ' + this.sessions.get(info).subjectMarks.get(subject);
            }

        }

        for (int i = 0; i < count; i++) {
            out.write(str[i]);
            out.write(System.getProperty("line.separator"));
        }

    }
    public boolean isGood()
    {
        for(String info: this.sessions.keySet())
            for(String subject: this.sessions.get(info).subjectMarks.keySet() )
                if(this.sessions.get(info).subjectMarks.get(subject)<4) return false;

        return true;
    }

}
