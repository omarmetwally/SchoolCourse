using System;
using System.Collections.Generic;

namespace SchoolProject_Api.Models
{
    public partial class Student
    {
        public Student()
        {
            Enroll = new HashSet<Enroll>();
            PrivateEnroll = new HashSet<PrivateEnroll>();
        }

        public Guid Sid { get; set; }
        public int? Esid { get; set; }
        public int UserTypeId { get; set; }

        public Stage Es { get; set; }
        public User S { get; set; }
        public UserTypeId UserType { get; set; }
        public ICollection<Enroll> Enroll { get; set; }
        public ICollection<PrivateEnroll> PrivateEnroll { get; set; }
    }
}
