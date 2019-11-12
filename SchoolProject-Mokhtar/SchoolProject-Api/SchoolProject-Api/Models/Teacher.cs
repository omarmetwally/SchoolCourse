using System;
using System.Collections.Generic;

namespace SchoolProject_Api.Models
{
    public partial class Teacher
    {
        public Teacher()
        {
            Class = new HashSet<Class>();
            PrivateClass = new HashSet<PrivateClass>();
            TeachingSubjectEs = new HashSet<TeachingSubjectEs>();
        }

        public Guid Tid { get; set; }
        public decimal Rank { get; set; }
        public string Description { get; set; }
        public int UserTypeId { get; set; }

        public User T { get; set; }
        public UserTypeId UserType { get; set; }
        public ICollection<Class> Class { get; set; }
        public ICollection<PrivateClass> PrivateClass { get; set; }
        public ICollection<TeachingSubjectEs> TeachingSubjectEs { get; set; }
    }
}
