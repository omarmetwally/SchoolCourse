using System;
using System.Collections.Generic;

namespace SchoolProject_Api.Models
{
    public partial class Branch
    {
        public Guid Bid { get; set; }
        public string Address { get; set; }
        public string Description { get; set; }
        public Guid Cid { get; set; }

        public Center C { get; set; }
    }
}
