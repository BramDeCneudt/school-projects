using Bindings;
using Cells;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ViewModel
{
    public class RangedDoubleParameterVM
    {
        private RangedDoubleParameter parameter;
        private ParameterBindings bindings;

        public RangedDoubleParameterVM(RangedDoubleParameter parameter, ParameterBindings bindings)
        {
            this.parameter = parameter;
            this.bindings = bindings;
        }

        public string Name => parameter.Id;

        public Cell<double> Value
        {
            get
            {
                return bindings.Read(parameter);
            }
        }

        public double Minimum => parameter.Minimum;
        public double Maximum => parameter.Maximum;
    }

 
}
