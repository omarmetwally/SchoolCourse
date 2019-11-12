using System;
using System.Collections.Generic;

namespace SchoolProject_Api.Models
{
    public partial class EducationalStage
    {
        public EducationalStage()
        {
            Student = new HashSet<Student>();
            TeachingSubjectEs = new HashSet<TeachingSubjectEs>();
        }

        public int Esid { get; set; }
        public string Esname { get; set; }

        public ICollection<Student> Student { get; set; }
        public ICollection<TeachingSubjectEs> TeachingSubjectEs { get; set; }
    }
}
