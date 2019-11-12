using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Internal;
using SchoolProject_Api.Models;

namespace SchoolProject_Api.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class UserSelectionController : ControllerBase
    {
        readonly private SchoolProjectDBContext context;

        public UserSelectionController(SchoolProjectDBContext _context)
        {
            context = _context;
        }

        [HttpGet, Route("center/{centerId}/{skipCounter}")]
        public IActionResult chooseCenter(Guid centerId, int skipCounter=0)
        {
           

            var result = context.TeachingSubjectEs.Join(
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
                    ).
                    Where(x => x.o.o.o.u.t.Cid == centerId)
                    .Select(f => new
                    {
                        tid = f.o.o.o.u.User.Uid,
                        firstname = f.o.o.o.u.User.FirstName,
                        lastname = f.o.o.o.u.User.LastName,
                        SubjectName = f.o.o.i.Subject.SubjectName,
                        ESName = f.i.StageName
                    }).Skip(skipCounter).Take(5);

            if (result.Count() == 0)
            {
                return NotFound();
            }

            var res = new Dictionary<object, ArrayList>();

            foreach (var x in result)
            {
                if (res.ContainsKey(new { x.tid, x.firstname, x.lastname }))
                {
                    var obj = res[new { x.tid, x.firstname, x.lastname }];
                    if (obj.Contains(x.SubjectName.Trim() + " / " + x.ESName.Trim()))
                        continue;
                    obj.Add(x.SubjectName.Trim() + " / " + x.ESName.Trim());
                }
                else
                {
                    ArrayList times = new ArrayList();
                    times.Add(x.SubjectName.Trim() + " / " + x.ESName.Trim());
                    res.Add(new { x.tid, x.firstname, x.lastname }, times);
                }
            }

            return Ok(res);
        }

        [HttpGet, Route("{TeacherID}/Centers/{skipCounter}")]
        public IActionResult TeacherCenter(Guid TeacherID, int skipCounter = 0)
        {

            var res = context.Class
                .Join(
                    context.User, 
                    myclass => myclass.CenterId,
                    user=> user.Uid,
                    (myclass, user) => new { myclass, user}
                )
                .Where(upper => upper.myclass.TeacherId==TeacherID)
                .Skip(skipCounter)
                .Take(5)
                .Select(select => new {
                    centerId = select.user.Uid,
                    firstname = select.user.FirstName,
                    lastname = select.user.LastName
                }).Distinct();

            return Ok(res);
        }

        [HttpGet, Route("{TeacherID}/{CenterId}/class/{skipCounter}")]
        public IActionResult TeacherCenterFunction(Guid TeacherID, Guid CenterId, int skipCounter = 0)
        {

            var res = context.Class
                .Where(teacher => teacher.TeacherId == TeacherID)
                .Where(center => center.CenterId == CenterId)
                .Skip(skipCounter)
                .Take(5)
                .Select(select => new
                {
                    subjectId = select.SubjectId,
                    classId = select.ClassId,
                    className = select.ClassName
                });
            return Ok(res);
        }

        [HttpGet, Route("{TeacherId}/subjects")]
        public IActionResult TeacherSubjectsMethod(Guid TeacherId)
        {

            var result = context.Class
                .Join(context.Subject,
                      myclass => myclass.SubjectId,
                      subject => subject.SubjectId,
                      (myclass, subject) => new { myclass, subject }
                    )
                    .Select(select => new
                    {
                        id = select.subject.SubjectId,
                        name = select.subject.SubjectName.Trim()
                    }).Distinct();

            return Ok(result);
        }

        [HttpGet, Route("{TeacherId}/{subjectId}")]
        public IActionResult TeacherSubjectId(Guid TeacherId, int subjectId)
        {
            var result = context.Class
            .Where(x => x.SubjectId == subjectId && x.TeacherId == TeacherId)
            .Select(select => new
            {
                classId = select.ClassId,
                location = select.Location,
                duration = select.Duration,
                start = select.StartDate,
                capacity = select.Capacity,
                price = select.Price
            });
            return Ok(result);
        }


    }
}