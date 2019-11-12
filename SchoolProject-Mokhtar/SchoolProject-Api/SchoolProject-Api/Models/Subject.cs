using System;
using System.Collections.Generic;

namespace SchoolProject_Api.Models
{
    public partial class Subject
    {
        public Subject()
        {
            EduactionSubject = new HashSet<EduactionSubject>();
            PrivateClass = new HashSet<PrivateClass>();
        }

        public int SubjectId { get; set; }
        public string SubjectName { get; set; }

        public ICollection<EduactionSubject> EduactionSubject { get; set; }
        public ICollection<PrivateClass> PrivateClass { get; set; }
    }
}
