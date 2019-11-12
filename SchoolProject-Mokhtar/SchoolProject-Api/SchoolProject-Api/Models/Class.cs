using System;
using System.Collections.Generic;

namespace SchoolProject_Api.Models
{
    public partial class Class
    {
        public Class()
        {
            Assignment = new HashSet<Assignment>();
            ClassMaterial = new HashSet<ClassMaterial>();
            Exam = new HashSet<Exam>();
        }

        public int ClassId { get; set; }
        public string ClassName { get; set; }
        public DateTime StartDate { get; set; }
        public TimeSpan Duration { get; set; }
        public int? Capacity { get; set; }
        public decimal Price { get; set; }
        public string Location { get; set; }
        public Guid CenterId { get; set; }
        public Guid TeacherId { get; set; }
        public int SubjectId { get; set; }

        public Center Center { get; set; }
        public EduactionSubject Subject { get; set; }
        public Teacher Teacher { get; set; }
        public ICollection<Assignment> Assignment { get; set; }
        public ICollection<ClassMaterial> ClassMaterial { get; set; }
        public ICollection<Exam> Exam { get; set; }
    }
}
