using Bindings;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ViewModel
{
    public class ParametersViewModel
    {
        public List<RangedDoubleParameterVM> Parameters { get;  }

        public ParametersViewModel(ParameterBindings bindings)
        {
            this.Parameters = bindings.Parameters.Where(p => p is RangedDoubleParameter).Select(p => new RangedDoubleParameterVM((RangedDoubleParameter)p, bindings)).ToList();
        }

        public RangedDoubleParameterVM getParameterVM(string name) 
        {
            return this.Parameters.First(p => p.Name.Equals(name));
        }

    }
}
