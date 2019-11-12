using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using SchoolProject_Api.Models;
using SchoolProject_Api.Utilities;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace SchoolProject_Api.Controllers
{
    [Route("api/[controller]")]
    public class ReservationController : Controller
    {

        readonly private SchoolProjectDBContext context;

        public ReservationController(SchoolProjectDBContext _context)
        {
            context = _context;
        }


        [HttpPost, Route("reserver")]
        public IActionResult reservePublic(ReserverDto reserverDto)
        {

            Guid id = UserService.getUID(reserverDto.Authorization);

            var enroll = context.Enroll.Add(
                    new Enroll
                    {
                        StudentId = id,
                        ClassId = reserverDto.classId,
                        EnrollDate = Convert.ToDateTime(DateTime.Now.ToString("MM-dd-yyyy")),
                        Status = false
                    }
                ).Entity;

            context.SaveChanges();

            return Ok(reserverDto.classId);
        }


        [HttpPost, Route("ViewRequest/")]
        public IActionResult ViewPublicRequest(ReserverDto reserverDto)
        {
            Guid id = UserService.getUID(reserverDto.Authorization);

            var requests = context.Enroll.Join(
                    context.Class,
                    EnrollClassId => EnrollClassId.ClassId,
                    myClass => myClass.ClassId,
                    (EnrollClassId, myClass) => new { EnrollClassId, myClass })
                    .Join(
                        context.User,
                        EnrollUser => EnrollUser.EnrollClassId.StudentId,
                        student => student.Uid,
                        (EnrollUser, student) => new { EnrollUser, student })
                    .Join(
                        context.User, 
                        gettingId => gettingId.EnrollUser.myClass.TeacherId,
                        settingTid => settingTid.Uid,
                        (gettingId, settingTid) => new {gettingId, settingTid})
                    .Where(x => x.gettingId.EnrollUser.EnrollClassId.Status == false && x.gettingId.EnrollUser.myClass.CenterId == id)
                    .Skip(reserverDto.skipCounter)
                    .Take(5)
                    .Select(select =>
                    new {
                        StudentId = select.gettingId.EnrollUser.EnrollClassId.StudentId,
                        ClassId = select.gettingId.EnrollUser.EnrollClassId.ClassId,
                        StudentFirstName = select.gettingId.student.FirstName,
                        StudentLastName = select.gettingId.student.LastName,
                        EnrollStatus = select.gettingId.EnrollUser.EnrollClassId.Status,
                        TeacherFirstName = select.settingTid.FirstName,
                        TeacherLastName = select.settingTid.LastName
                    }
                    );
                        
            return Ok(requests);
        }

        [HttpPut, Route("AcceptRequest/")]
        public IActionResult Accepting(RequestDTO acceptionDTO)
        {
            var sid = new Guid(acceptionDTO.sid); 
            var entity = context.Enroll.SingleOrDefault(x => x.StudentId == sid && x.ClassId == acceptionDTO.cid);
            entity.Status = true;
            context.Enroll.Update(entity);
            context.SaveChanges();
            return Ok("Done");
        }

        [HttpDelete, Route("RejectRequest")]
        public IActionResult RejectRequest(RequestDTO rejectDto)
        {
            var sid = new Guid(rejectDto.sid);
            context.Enroll.Remove(context.Enroll.SingleOrDefault(x => x.StudentId == sid && x.ClassId == rejectDto.cid));
            context.SaveChanges();
            return Ok("Done!");
        }


    }

    public class RequestDTO
    {
        public string sid { get; set; }
        public int cid { get; set; }
    }


    public class ReserverDto
    {
        [FromHeader] public string Authorization { get; set; }
        [FromForm]public int classId { get; set; }
        [FromForm] public int skipCounter { get; set; }
    }
}