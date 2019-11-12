using System;
using System.Collections.Generic;

namespace SchoolProject_Api.Models
{
    public partial class Stage
    {
        public Stage()
        {
            EduactionSubject = new HashSet<EduactionSubject>();
            Student = new HashSet<Student>();
        }

        public int StageId { get; set; }
        public string StageName { get; set; }

        public ICollection<EduactionSubject> EduactionSubject { get; set; }
        public ICollection<Student> Student { get; set; }
    }
}
