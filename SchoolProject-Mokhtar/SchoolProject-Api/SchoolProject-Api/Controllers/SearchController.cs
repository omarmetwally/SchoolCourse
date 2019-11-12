using System.Collections;
using System.Collections.Generic;
using System.Linq;
using Microsoft.AspNetCore.Mvc;
using SchoolProject_Api.Models;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace SchoolProject_Api.Controllers
{
    [Route("api/[controller]")]
    public class SearchController : Controller
    {

        readonly private SchoolProjectDBContext context;
        private object key1;

        public SearchController(SchoolProjectDBContext _context)
        {
            context = _context;
        }
        [HttpGet, Route("{subjectId}/Teachers")]
        public IActionResult TeachersBySubject(int subjectId)
        {
            var result = context.Class
                         .Join(context.User,
                                myclass => myclass.TeacherId,
                                uid => uid.Uid,
                                (myclass, uid) => new { myclass, uid })
                                .Join(context.Teacher,
                                    upper => upper.myclass.TeacherId,
                                    teacher => teacher.Tid,
                                    (upper, teacher) => new { upper, teacher })
                                    .Select(select => new
                                    {
                                        teacherId = select.teacher.Tid,
                                        teacherFirstName = select.upper.uid.FirstName,
                                        teacherLastName = select.upper.uid.LastName,
                                        teacherRank = select.teacher.Rank
                                    }).Distinct();
            return Ok(result);
        }

        //main Search screen 
        [HttpGet("GetTeacherNamed/")]
        public IActionResult GetTeacherNamed() {

            var TeacherList = context.User
                .Join(context.Teacher,
                    user => user.Uid,
                    teacher => teacher.Tid,
                    (user, teacher) => new { user, teacher }
                )
                .Where(x => x.user.UserTypeId == 1)
                .Select(select => new {
                    tid = select.user.Uid,
                    tFirstName = select.user.FirstName,
                    tLastName = select.user.LastName,
                    tRank = select.teacher.Rank
                })
                .OrderBy(x => x.tRank)
                .Take(10);

            if (TeacherList.Count() == 0) return Ok("Sorry nothing found");

            return Ok(TeacherList);
        }


        //search by name;
        [HttpGet("GetTeacherNamed/{name}/{skip}")]
        public IActionResult GetTeacherNamed(string name, int skip = 0) {

            var TeacherList = context.User
                .Join(context.Teacher,
                    user => user.Uid,
                    teacher => teacher.Tid,
                    (user, teacher) => new { user, teacher }
                )
                .Where(x => x.user.UserTypeId == 1 && (x.user.FirstName == name || x.user.LastName == name))
                .Select(select => new {
                    tid = select.user.Uid,
                    tFirstName = select.user.FirstName,
                    tLastName = select.user.LastName,
                    tRank = select.teacher.Rank
                   
                })
                .OrderBy(x => x.tRank)
                .Take(5)
                .Skip(skip * 5);

            if (TeacherList.Count() == 0) return Ok("Sorry nothing found");

            return Ok(TeacherList);
        }

        //search by subject

        [HttpGet("GetTeacherSubject/{subjectId}/{skip}")]
        public IActionResult GetTeacherSubject(int subjectId, int skip) {

            var TeacherList = context.TeachingSubjectEs
                .Join(context.User,
                    es => es.Tid,
                    user => user.Uid,
                    (es, user) => new { es, user }
                )
                .Join(context.Teacher,
                    user => user.user.Uid,
                    teacher => teacher.Tid,
                    (user, teacher) => new { user, teacher }
                )
                .Where(x => x.user.es.Es == subjectId)
                .Select(select => new {
                    tid = select.user.user.Uid,
                    tFirstName = select.user.user.FirstName,
                    tLastName = select.user.user.LastName,
                    tRank = select.teacher.Rank
                })
                .OrderBy(x => x.tRank)
                .Take(5)
                .Skip(skip * 5);

            if (TeacherList.Count() == 0) return Ok("Sorry nothing found");
            return Ok(TeacherList);
        }

        //search by Region

        [HttpGet("GetTeacherRegion/{region}/{skip}")]
        public IActionResult GetTeacherRegion(string region, int skip) {

            var TeacherList = context.User
                  .Join(context.Teacher,
                      user => user.Uid,
                      teacher => teacher.Tid,
                      (user, teacher) => new { user, teacher }
                  )
                  .Where(x => x.user.UserTypeId == 1 && x.user.Region == region)
                  .Select(select => new {
                      tid = select.user.Uid,
                      tFirstName = select.user.FirstName,
                      tLastName = select.user.LastName,
                      tRank = select.teacher.Rank
                  })
                  .OrderBy(x => x.tRank)
                  .Take(5)
                  .Skip(skip * 5);

            if (TeacherList.Count() == 0) return Ok("Sorry nothing found");
            return Ok(TeacherList);
        }

        //search by City

        [HttpGet("GetTeacherCity/{city}/{skip}")]
        public IActionResult GetTeachercity(string city, int skip) {

            var TeacherList = context.User
                  .Join(context.Teacher,
                      user => user.Uid,
                      teacher => teacher.Tid,
                      (user, teacher) => new { user, teacher }
                  )
                  .Where(x => x.user.UserTypeId == 1 && x.user.City == city)
                  .Select(select => new {
                      tid = select.user.Uid,
                      tFirstName = select.user.FirstName,
                      tLastName = select.user.LastName,
                      tRank = select.teacher.Rank
                  })
                  .OrderBy(x => x.tRank)
                  .Take(5)
                  .Skip(skip * 5);

            if (TeacherList.Count() == 0) return Ok("Sorry nothing found");
            return Ok(TeacherList);
        }

        /*
        
            CENTER
        
         */

        //search by name;
        [HttpGet("GetCenterNamed/{name}/{skip}")]
        public IActionResult GetCenterNamed(string name, int skip = 0) {

            var TeacherList = context.User
                .Join(context.Center,
                    user => user.Uid,
                    Center => Center.Cid,
                    (user, Center) => new { user, Center }
                )
                .Where(x => x.user.UserTypeId == 1 && (x.user.FirstName == name || x.user.LastName == name))
                .Select(select => new {
                    cid = select.user.Uid,
                    cFirstName = select.user.FirstName,
                    cLastName = select.user.LastName,
                    cRate = select.Center.Rate
                })
                .OrderBy(x => x.cRate)
                .Take(5)
                .Skip(skip * 5);

            if (TeacherList.Count() == 0) return Ok("Sorry nothing found");

            return Ok(TeacherList);
        }

        //search by Region

        [HttpGet("GetCenterRegion/{region}/{skip}")]
        public IActionResult GetCenterRegion(string region, int skip) {

            var TeacherList = context.User
                  .Join(context.Center,
                      user => user.Uid,
                      center => center.Cid,
                      (user, center) => new { user, center }
                  )
                  .Where(x => x.user.UserTypeId == 1 && x.user.Region == region)
                  .Select(select => new {
                      cid = select.user.Uid,
                      cFirstName = select.user.FirstName,
                      cLastName = select.user.LastName,
                      cRate = select.center.Rate
                  })
                  .OrderBy(x => x.cRate)
                  .Take(5)
                  .Skip(skip * 5);

            if (TeacherList.Count() == 0) return Ok("Sorry nothing found");
            return Ok(TeacherList);
        }

        //search by City

        [HttpGet("GetCenterCity/{city}/{skip}")]
        public IActionResult GetCenterCity(string city, int skip) {

            var TeacherList = context.User
                  .Join(context.Center,
                      user => user.Uid,
                      center => center.Cid,
                      (user, center) => new { user, center }
                  )
                  .Where(x => x.user.UserTypeId == 1 && x.user.City == city)
                  .Select(select => new {
                      cid = select.user.Uid,
                      cFirstName = select.user.FirstName,
                      cLastName = select.user.LastName,
                      cRate = select.center.Rate
                  })
                  .OrderBy(x => x.cRate)
                  .Take(5)
                  .Skip(skip * 5);

            if (TeacherList.Count() == 0) return Ok("Sorry nothing found");
            return Ok(TeacherList);
        }

        // get all teachers and subject they teach in each center

        [HttpGet("AllTeachersSubjectsInCenter")]

        public IActionResult AllTeachersSubjectsInCenter()
        {


            ArrayList times = new ArrayList();

            times.Add("Math");
            times.Add("English");
        

            var result = context.TeachingSubjectEs.Join(

                context.User,
                TS => TS.Tid,
                US => US.Uid,
                (TS, US) => new { TS, US }


                ).Join(
                    context.User,
                    CS => CS.TS.Cid,
                    CTS => CTS.Uid,
                    (CS, CTS) => new { CS, CTS }

                ).Join(
                    context.EduactionSubject,
                    TSES => TSES.CS.TS.Es,
                    ES => ES.Esid,
                    (TSES, ES) => new { TSES, ES }

                ).Join(
                    context.Subject,
                    SubjES => SubjES.ES.SubjectId,
                    subj => subj.SubjectId,
                    (SubjES, subj) => new { SubjES, subj }

                ).Join(
                    context.Stage,
                    StageES => StageES.SubjES.ES.StageId,
                    stage => stage.StageId,
                    (StageES, stage) => new { StageES, stage }

                )
                .Select(f => new {

                    tid = f.StageES.SubjES.TSES.CS.TS.Tid,
                    tFirstName = f.StageES.SubjES.TSES.CS.US.FirstName,
                    tLastName = f.StageES.SubjES.TSES.CS.US.LastName,
                    centerName = f.StageES.SubjES.TSES.CTS.FirstName,
                    //subjectName = f.StageES.subj.SubjectName,
                    subjectName = times,
                    stageName = f.stage.StageName
             


        });







            return Ok(result);
        }
        [HttpGet("AllTeachersSubjectsInCenter2")]
        public IActionResult AllTeachersSubjectsInCenter2()
        {

            var result = context.TeachingSubjectEs.Join(

                context.User,
                TS => TS.Tid,
            
                US => US.Uid,
                (TS, US) => new { TS, US }


                ).Join(
                    context.User,
                    CS => CS.TS.Cid,
                    CTS => CTS.Uid,
                    (CS, CTS) => new { CS, CTS }

                ).Join(
                    context.EduactionSubject,
                    TSES => TSES.CS.TS.Es,
                    ES => ES.Esid,
                    (TSES, ES) => new { TSES, ES }

                ).Join(
                    context.Subject,
                    SubjES => SubjES.ES.SubjectId,
                    subj => subj.SubjectId,
                    (SubjES, subj) => new { SubjES, subj }

                ).Join(
                    context.Stage,
                    StageES => StageES.SubjES.ES.StageId,
                    stage => stage.StageId,
                    (StageES, stage) => new { StageES, stage }

                )
                .Select(f => new {

                    tid = f.StageES.SubjES.TSES.CS.TS.Tid,
                    tFirstName = f.StageES.SubjES.TSES.CS.US.FirstName,
                    tLastName = f.StageES.SubjES.TSES.CS.US.LastName,
                    CenterName = f.StageES.SubjES.TSES.CTS.FirstName,
                    subjectName = f.StageES.subj.SubjectName,
                    stageName = f.stage.StageName

                });

            if (result.Count() == 0)
            {
                return NotFound();
            }

            var res = new Dictionary<object, ArrayList>();

            foreach (var x in result)
            {
                if (res.ContainsKey(new { x.tid, x.tFirstName, x.tLastName }))
                {
                    var obj = res[new { x.tid, x.tFirstName, x.tLastName }];
                    if (obj.Contains(x.subjectName.Trim() + " / " + x.stageName.Trim() + " / " + x.CenterName.Trim()))
                        continue;
                    obj.Add(x.CenterName.Trim() + " / " + x.subjectName.Trim() + " / " + x.stageName.Trim());
                }
                else
                {
                    ArrayList times = new ArrayList();
                    times.Add(x.CenterName.Trim() + " / " + x.subjectName.Trim() + " / " + x.stageName.Trim());
                    res.Add(new { x.tid, x.tFirstName, x.tLastName }, times);
                }
            }

            return Ok(res);





            //return Ok(res);
        }
        [HttpGet("AllTeachersSubjectsInCenter3")]
        public IActionResult AllTeachersSubjectsInCenter3()
        {

            ArrayList centers = new ArrayList();
            ArrayList subjects = new ArrayList();

            var result = context.TeachingSubjectEs.Join(

                context.User,
                TS => TS.Tid,
                US => US.Uid,
                (TS, US) => new { TS, US }


                ).Join(
                    context.User,
                    CS => CS.TS.Cid,
                    CTS => CTS.Uid,
                    (CS, CTS) => new { CS, CTS }

                ).Join(
                    context.EduactionSubject,
                    TSES => TSES.CS.TS.Es,
                    ES => ES.Esid,
                    (TSES, ES) => new { TSES, ES }

                ).Join(
                    context.Subject,
                    SubjES => SubjES.ES.SubjectId,
                    subj => subj.SubjectId,
                    (SubjES, subj) => new { SubjES, subj }

                ).Join(
                    context.Stage,
                    StageES => StageES.SubjES.ES.StageId,
                    stage => stage.StageId,
                    (StageES, stage) => new { StageES, stage }


                );






            var teacherDetails = result.Select(f => new
            {
                firstName = f.StageES.SubjES.TSES.CS.US.FirstName,
                lastName = f.StageES.SubjES.TSES.CS.US.LastName

            }).Distinct();




            var subjcetResult = result.GroupBy(
                    p => p.StageES.SubjES.TSES.CS.US.Uid,
                    p => p.StageES.SubjES.ES.Subject.SubjectName,

                    (key, g) => new { Subjects = g.ToList() }

                ).Distinct();

            var centerResult = result.GroupBy(
                    p => p.StageES.SubjES.TSES.CS.US.Uid,
                    p => p.StageES.SubjES.TSES.CTS.FirstName,

                    (key, g) => new { Centers = g.ToList() }

                ).Distinct();


            //var finalResult = new searchResultListDto();
            //finalResult.TechDetails = teacherDetails;
            //finalResult.SubjectsTeach = subjcetResult;
            //finalResult.CentersAttend = centerResult;

            List<dynamic> finalResult = new List<dynamic>();
            finalResult.Add(teacherDetails);
            finalResult.Add(subjcetResult);
            finalResult.Add(centerResult);



            if (result.Count() == 0)
            {
                return NotFound();
            }




            // x.StageES.SubjES.TSES.CTS.FirstName
            //x.StageES.SubjES.ES.Subject.SubjectName



            return Ok(finalResult);





        }

        [HttpGet, Route("getTeachersWithCenter44")]
        public IActionResult chooseCenter()
        {


            var results = context.TeachingSubjectEs.Join(
                    context.User,
                    t => t.Tid,
                    User => User.Uid,
                    (t, User) => new { t, User }
                ).Join(
                context.User,
                u => u.User.Uid,
                t => t.Uid,
                (u, t) => new { u, t })
                .Join(
                    context.EduactionSubject,
                    o => o.u.t.Es,
                    i => i.Esid,
                    (o, i) => new { o, i }
                    )
                .Join(context.Subject,
                    o => o.i.SubjectId,
                    i => i.SubjectId,
                    (o, i) => new { o })
                    .Join(context.Stage,
                     o => o.o.i.StageId,
                     i => i.StageId,
                     (o, i) => new { o, i }
                    ).Join(context.User,
                        o => o.o.o.o.u.t.Cid,
                        tid => tid.Uid,
                        (o, tid) => new { o, tid }
                    )
                    .Select(f => new
                    {
                        tid = f.o.o.o.o.u.User.Uid,
                        firstname = f.o.o.o.o.u.User.FirstName,
                        lastname = f.o.o.o.o.u.User.LastName,
                        SubjectName = f.o.o.o.i.Subject.SubjectName,
                        ESName = f.o.i.StageName,
                        CenterName = (f.tid.FirstName)
                    }).GroupBy(p => p.tid);

            if (results.Count() == 0)
            {
                return NotFound();
            }
            var myResponse = new List<Dictionary<string, dynamic>>();
            int incr = 0;
            foreach (var result in results)
            {
                int SubjectCounter = 0;
                foreach (var eachResult in result)
                {

                    myResponse.Add(new Dictionary<string, dynamic>());
                    if (!myResponse[incr].ContainsValue(eachResult.firstname))
                    {
                        myResponse[incr].Add("tid", eachResult.tid.ToString());

                        myResponse[incr].Add("tFirstName", eachResult.firstname);
                        myResponse[incr].Add("tLastName", eachResult.lastname);
                    }
                    if (!myResponse[incr].ContainsKey("Subject"))
                    {
                        List<string> subjectList = new List<string>();
                        subjectList.Add(eachResult.SubjectName.Trim());
                        myResponse[incr].Add("Subject", subjectList);

                    }
                    else
                    {
                        List<string> Subjectlist = myResponse[incr]["Subject"];
                        Subjectlist.Add(eachResult.SubjectName.Trim());

                    }
                    if (!myResponse[incr].ContainsKey("stageName"))
                    {

                        List<string> stageList = new List<string>();
                        stageList.Add(eachResult.ESName.Trim());
                        myResponse[incr].Add("stageName", stageList);
                    }
                    else
                    {
                        List<string> Stagelist = myResponse[incr]["stageName"];
                        Stagelist.Add(eachResult.ESName.Trim());
                    }
                    if (!myResponse[incr].ContainsKey("centerName"))
                    {

                        List<string> CenterList = new List<string>();
                        CenterList.Add(eachResult.CenterName.Trim());
                        myResponse[incr].Add("centerName", CenterList);
                    }
                    else
                    {
                        List<string> CenterList = myResponse[incr]["centerName"];
                        CenterList.Add(eachResult.CenterName.Trim());
                    }
                }
                incr++;
            }

            myResponse.RemoveAt(myResponse.Count - 1);
            return Ok(myResponse);
        }
    }
}
