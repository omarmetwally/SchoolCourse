using System;
using System.Collections.Generic;

namespace SchoolProject_Api.Models
{
    public partial class Center
    {
        public Center()
        {
            Branch = new HashSet<Branch>();
            Class = new HashSet<Class>();
            TeachingSubjectEs = new HashSet<TeachingSubjectEs>();
        }

        public Guid Cid { get; set; }
        public int UserTypeId { get; set; }
        public decimal? Rate { get; set; }
        public string Description { get; set; }

        public User C { get; set; }
        public UserTypeId UserType { get; set; }
        public ICollection<Branch> Branch { get; set; }
        public ICollection<Class> Class { get; set; }
        public ICollection<TeachingSubjectEs> TeachingSubjectEs { get; set; }
    }
}
