using System;
using System.Collections.Generic;

namespace SchoolProject_Api.Models
{
    public partial class PrivateEnroll
    {
        public Guid StudentId { get; set; }
        public int PrivateClassId { get; set; }
        public DateTime EnrollDate { get; set; }
        public bool Status { get; set; }

        public PrivateClass PrivateClass { get; set; }
        public Student Student { get; set; }
    }
}
