//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated from a template.
//
//     Manual changes to this file may cause unexpected behavior in your application.
//     Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace Bike.Models
{
    using System;
    using System.Collections.Generic;
    
    public partial class Quotation
    {
        public int CustomerNo { get; set; }
        public string CustomerName { get; set; }
        public string Address { get; set; }
        public string BikeName { get; set; }
        public Nullable<decimal> Price { get; set; }
        public Nullable<decimal> Total { get; set; }
    }
}
