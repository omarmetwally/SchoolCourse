using System.Collections.Generic;
using System.Linq;
using Microsoft.AspNetCore.Mvc;
using SchoolProject_Api.Models;


namespace SchoolProject_Api.Controllers {
    [Route("api/[controller]")]

    public class CourseMaterial: Controller {
        readonly private SchoolProjectDBContext context;

        public CourseMaterial(SchoolProjectDBContext _context){
            context = _context;
        }

        [HttpGet("GetMaterial/{ClassId}/{skip}")]
        public IActionResult GetMaterial(int classId, int skip=0){
            
            var materials = context.ClassMaterial.Where(x => x.ClassId == classId).Take(5).Skip(skip*5).ToList();

            if(materials.Count == 0){
                return Ok("there's no material");
            }
            return Ok(materials);
        }

        [HttpGet("GetAssignments/{ClassId}/{skip}")]
        public IActionResult GetAssignments(int ClassId, int skip){
            var assignments = context.Assignment.Where(x=> x.ClassId == ClassId).Take(5).Skip(skip*5).ToList();
            if(assignments.Count == 0)  return Ok("Sorry, no assignments for you, yet!");
            return Ok(assignments);
        }

        [HttpGet("GetExams/{ClassId}/{skip}")]
        public IActionResult GetExams(int ClassId, int skip){
            var exams = context.Exam.Where(x=> x.ClassId == ClassId ).OrderByDescending(x=> x.ExamDate).Take(5).Skip(skip).ToList();
            if(exams.Count == 0) return Ok("No exams");
            return Ok(exams);
        }
        
    }

}