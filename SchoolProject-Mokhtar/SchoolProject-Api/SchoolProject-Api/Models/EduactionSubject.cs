using System;
using System.Collections.Generic;

namespace SchoolProject_Api.Models
{
    public partial class EduactionSubject
    {
        public EduactionSubject()
        {
            Class = new HashSet<Class>();
            TeachingSubjectEs = new HashSet<TeachingSubjectEs>();
        }

        public int Esid { get; set; }
        public int SubjectId { get; set; }
        public int StageId { get; set; }

        public Stage Stage { get; set; }
        public Subject Subject { get; set; }
        public ICollection<Class> Class { get; set; }
        public ICollection<TeachingSubjectEs> TeachingSubjectEs { get; set; }
    }
}
