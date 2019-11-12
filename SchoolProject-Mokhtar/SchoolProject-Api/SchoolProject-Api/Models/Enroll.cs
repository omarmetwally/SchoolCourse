using System;
using System.Collections.Generic;

namespace SchoolProject_Api.Models
{
    public partial class Enroll
    {
        public Guid StudentId { get; set; }
        public int ClassId { get; set; }
        public DateTime EnrollDate { get; set; }
        public bool Status { get; set; }

        public Student Student { get; set; }
    }
}
