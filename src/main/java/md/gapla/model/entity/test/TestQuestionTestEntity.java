//package md.gapla.model.entity.test;
//
//import jakarta.persistence.*;
//import lombok.Data;
//import md.gapla.model.entity.LanguageEntity;
//
//@Entity
//@Table(name = "testquestiontest", schema = "public")
//@Data
//public class TestQuestionTestEntity {
//    @Id
//    @Column(name = "testquestiontestid")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long testSId;
//
//
//    @ManyToOne
//    @JoinColumn(name = "testid", referencedColumnName = "testid")
//    private TestEntity test;
//
//
//    @ManyToOne
//    @JoinColumn(name = "testquestionid", referencedColumnName = "testquestionid")
//    private TestQuestionEntity testQuestion;
//
//
//
//}
