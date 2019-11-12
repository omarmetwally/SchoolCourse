using System;
using System.Collections.Generic;

namespace SchoolProject_Api.Models
{
    public partial class ClassMaterial
    {
        public int PdfId { get; set; }
        public string MaterialName { get; set; }
        public int ClassId { get; set; }
        public string PdfLinks { get; set; }

        public Class Class { get; set; }
    }
}
