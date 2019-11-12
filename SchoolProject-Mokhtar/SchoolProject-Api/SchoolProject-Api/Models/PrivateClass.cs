using System;
using System.Collections.Generic;

namespace SchoolProject_Api.Models
{
    public partial class PrivateClass
    {
        public PrivateClass()
        {
            PrivateEnroll = new HashSet<PrivateEnroll>();
        }

        public int PrivateClassId { get; set; }
        public string ClassName { get; set; }
        public DateTime StartDate { get; set; }
        public TimeSpan Duration { get; set; }
        public int? Capacity { get; set; }
        public decimal Price { get; set; }
        public string Location { get; set; }
        public Guid TeacherId { get; set; }
        public int SubjectId { get; set; }

        public Subject Subject { get; set; }
        public Teacher Teacher { get; set; }
        public ICollection<PrivateEnroll> PrivateEnroll { get; set; }
    }
}
